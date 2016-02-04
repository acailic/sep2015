package org.sep.issuer.form.controllers;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.criteria.Order;

import org.sep.issuer.form.dto.IssuerOrderDTO;
import org.sep.issuer.form.dto.TransactionResponseDTO;
import org.sep.issuer.form.model.Account;
import org.sep.issuer.form.model.Card;
import org.sep.issuer.form.model.Issuer;
import org.sep.issuer.form.model.IssuerOrder;
import org.sep.issuer.form.model.IssuerOrder.OrderStateEnum;
import org.sep.issuer.form.model.User;
import org.sep.issuer.form.service.AccountService;
import org.sep.issuer.form.service.CardService;
import org.sep.issuer.form.service.IssuerOrderService;
import org.sep.issuer.form.service.IssuerService;
import org.sep.issuer.form.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/issuerorder")
public class IssuerOrderController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CardService cardService;	
	@Autowired
	private IssuerOrderService issuerOrderService;
	@Autowired
	private IssuerService issuerService;
	@Autowired
	private UserService userService;
	
	

	private final Logger logger = LoggerFactory.getLogger(IssuerOrderController.class);
	private static final String DECLINE = "Issuer Declined Transaction - reason: ";
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> acceptTransaction(@RequestBody String request) {
		
		logger.info("ISSUER: new transaction entered");
		ObjectMapper mapper = new ObjectMapper();
		IssuerOrder order = null;
		
		Card temp;
		if( (temp = cardService.find(1L)) == null) 
			fillDB();
		
		try{
			
			IssuerOrderDTO dto = mapper.readValue(request, IssuerOrderDTO.class);
			
			if(dto.getAcquirerOrderId() == null || dto.getAcquirerOrderId() < 1 || 
				dto.getAcquirerOrderTimestamp() == null || dto.getCardExpDate() == null ||
				dto.getAmount() == null || dto.getAmount() < 0 || dto.getCardSecCode() == null ||
				dto.getCardHolderName() == null || dto.getCardHolderName().isEmpty() ){
				logger.info(DECLINE + "Request is incomplete!");
				return new ResponseEntity<String>("Request is incomplete!", HttpStatus.BAD_GATEWAY);
			}
			if(dto.getPan() == null || dto.getPan().isEmpty()){
				logger.info(DECLINE + "PAN is empty!");
				return new ResponseEntity<String>("PAN is not well formatted!", HttpStatus.BAD_GATEWAY);
			}	
			if(!checkPAN(dto.getPan())){
				logger.info(DECLINE + "PAN is not valid!");
				return new ResponseEntity<String>("PAN is not valid!", HttpStatus.BAD_GATEWAY);
			}					
			
			//TODO: fix nullpointerex
			//Card card = cardService.findByPan(dto.getPan());
			Card card = null;
			ArrayList<Card> cards = (ArrayList<Card>) cardService.findAll();
			for(Card crd : cards){
				if(crd.getPan().equals(dto.getPan())){
					card = crd;
					break;
				}
			}
			
			if(card == null){
				logger.info(DECLINE + "Credit Card does not exist!");
				return new ResponseEntity<String>("Credit Card does not exist!", HttpStatus.BAD_GATEWAY);
			}

			card.setCardExpDate( trimDate(card.getCardExpDate()));
			dto.setCardExpDate( trimDate(dto.getCardExpDate()));	
			
			
			if(!dto.getCardSecCode().equals( checkSecCode(card.getPan(), card.getCardExpDate())  )){
				logger.info(DECLINE + "Unauthorised!");
				return new ResponseEntity<String>("Unauthorised!", HttpStatus.BAD_REQUEST);
			}

			
//			logger.info(card.getCardExpDate() + "::::" + dto.getCardExpDate());
			
//			if(!card.getCardExpDate().equals(dto.getCardExpDate())){
//				logger.info(DECLINE + "Credit Card expiration date is wrong!");
//				return new ResponseEntity<String>("Credit Card expiration date is wrong!", HttpStatus.BAD_GATEWAY);
//			}

			if(card.getCardExpDate().before(new Date())){
				logger.info(DECLINE + "Credit Card is expired!");
				return new ResponseEntity<String>("Credit Card is expired!", HttpStatus.BAD_GATEWAY);
			}
			if(card.getAccount() == null){
				logger.info(DECLINE + "Credit Card is not connected to account!");
				return new ResponseEntity<String>("Credit Card is not connected to account!", HttpStatus.BAD_GATEWAY);
			}
			if(card.getAccount().getBalance() < dto.getAmount()){
				logger.info(DECLINE + "Insufficient funds!");
				return new ResponseEntity<String>("Insufficient funds!", HttpStatus.BAD_GATEWAY);
			}
			
			Account acc = card.getAccount();
			acc.setBalance(acc.getBalance() - dto.getAmount());
			logger.info(acc.toString());
			acc = accountService.update(acc);
			
			order = new IssuerOrder(dto);
			order.setCard(card);
			order.setIssuerOrderTimestamp(new Date());
			order.setState(OrderStateEnum.SUCCESSFULL);
			order = issuerOrderService.save(order);
			
			logger.info("Issuer Confirmed Transaction id: " + order.getId());
						
			return new ResponseEntity<Object>(new TransactionResponseDTO(order), HttpStatus.OK);
						
		}catch(Exception ex){
			logger.error(ex.toString());
			
			if(order != null){
				order.setState(OrderStateEnum.UNSUCCESSFULL);
				issuerOrderService.save(order);				
			}
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




	private boolean checkPAN(String pan) {
		char[] panArray = pan.toCharArray();
		
		int pass = 0;
		int digit;
		int sum = 0;
		for(int i = panArray.length - 2; i >= 0; i--, pass++){ //first from right is skipped (check digit)

        	digit = Character.getNumericValue(panArray[i]);
        	
	        if(pass % 2 == 0) {
	            digit = digit * 2;
	       
		        if(digit > 9) {
		            digit = digit - 9; // get the cossfoot - Exp: 10 - 9 = 1 + 0 | 12 - 9 = 1 + 2 | ... | 18 - 9 = 1 + 8
		        }		        
	        }
	        
	        sum += digit;
	        
		}
		logger.info("PAN check sum: " + String.valueOf(sum));
		if(sum % 10 == 0) // pan is valid
			return true;
		else
			return false;
	}


	private String checkSecCode(String pan, Date cardExpDate) {
		String encoded;
		Integer csc = null, passes = 3;
		try {
			Cipher encript = Cipher.getInstance("DES");
			encript.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("IssuerSK".getBytes(), "DES"));
			byte[] byteArrayPan = pan.getBytes("UTF8");
			byte[] byteArrayDate = cardExpDate.toString().getBytes("UTF8");
			
			encoded = new sun.misc.BASE64Encoder().encode( encript.doFinal(byteArrayPan));
			encoded.concat( new sun.misc.BASE64Encoder().encode( encript.doFinal(byteArrayDate)));
						
			int lengt = encoded.length();
			csc = (encoded.charAt(0) % 9) * 100;
			csc += (encoded.charAt(lengt/2) % 9) * 10;
			csc += (encoded.charAt(lengt-1) % 9);

			csc = 0;
			for(int i = 0; i < passes; i++){
				int temp = 0;
				for(char c : encoded.toCharArray()){
					temp = ((i * temp) + c) % 9; 
				}
				if(i == 0)
					temp *= 100;
				else if( i == 1)
					temp *= 10;
				
				csc += temp;				
			}
			
			logger.info(encoded);
			logger.info(String.valueOf(csc));
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		 It is calculated by encrypting the bank card number and expiration date 
		return String.valueOf(csc);
	}	
	

	@SuppressWarnings("deprecation")
	private Date trimDate(Date cardExpDate) {
		cardExpDate.setDate(1);
		cardExpDate.setHours(0);
		cardExpDate.setMinutes(0);
		cardExpDate.setSeconds(0);
		return cardExpDate;
	}
	
	private void fillDB() {
		try {
			
			Issuer iss = new Issuer("OTP Banka", "http://localhost:8083/Issuer/issuerorder",
				"112233", "NS bul", "021", "otp@gmail.com");
		
			iss = issuerService.save(iss);
				
			User user = new User("1412992820306", "Strahinja", "Stanivuk", "NS det", "069", "ssstanivuk@gmail.com");
			user = userService.save(user);
			
			Account acc = new Account(190000.0, 789456132L);
			acc.setIssuer(iss);
			acc.setUser(user);
			acc = accountService.save(acc);
			
			Card card = new Card("1234123412341234", "Strahinja", new Date(2020, 0, 1));
			card.setAccount(acc);
			card.setUser(user);
			card = cardService.save(card);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
