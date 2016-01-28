package org.sep.pcc.form.controllers;

import java.util.ArrayList;

import org.sep.pcc.form.dto.IssuerRequestDTO;
import org.sep.pcc.form.dto.IssuerResponseDTO;
import org.sep.pcc.form.dto.PccResponseDTO;
import org.sep.pcc.form.dto.TransactionDTO;
import org.sep.pcc.form.model.Bank;
import org.sep.pcc.form.model.Transaction;
import org.sep.pcc.form.model.Transaction.OrderStateEnum;
import org.sep.pcc.form.service.BankService;
import org.sep.pcc.form.service.TransactionService;
import org.sep.pcc.form.util.HeaderUtil;
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
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {
	

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	private static final String DECLINE = "PCC Declined Transaction - reason: ";
	
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private BankService bankService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> acceptTransaction(@RequestBody String request) {
		logger.info("new transaction entered");
		Transaction tr = null;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemp = new RestTemplate();
		restTemp.setErrorHandler(new DefaultResponseErrorHandler(){
		    protected boolean hasError(HttpStatus statusCode) {
		        return false;
		    }
		});

		PccResponseDTO result = new PccResponseDTO();
		String errorMsg;
		try{

			TransactionDTO dto = mapper.readValue(request, TransactionDTO.class);
			
			result.setAcquirerOrderId(dto.getAcquirerOrderId());
			result.setAcquirerOrderTimestamp(dto.getAcquirerOrderTimestamp());
			result.setState(OrderStateEnum.UNSUCCESSFULL);
			
			if(dto.getAcquirerId() == null || dto.getAcquirerId() < 1 ||
				dto.getAcquirerOrderId() == null || dto.getAcquirerOrderId() < 1 || 
				dto.getAcquirerOrderTimestamp() == null || dto.getCardExpDate() == null ||
				dto.getAmount() == null || dto.getAmount() < 0 || dto.getCardSecCode() == null ||
				dto.getCardHolderName() == null || dto.getCardHolderName().isEmpty() ){
				errorMsg = "Request is incomplete!";
				logger.info(DECLINE + errorMsg);
				result.setErrorMsg(errorMsg);
				return new ResponseEntity<PccResponseDTO>(result, HttpStatus.BAD_REQUEST);
			}
			
			
			Bank acquirer = bankService.find(dto.getAcquirerId());
			if(acquirer == null){
				errorMsg = "Acquirer does not exist!";
				logger.info(DECLINE + errorMsg);
				result.setErrorMsg(errorMsg);
				return new ResponseEntity<PccResponseDTO>(result, HttpStatus.BAD_REQUEST);
			}
			
			if(dto.getPan() == null || dto.getPan().isEmpty() || dto.getPan().length() != 16){
				errorMsg = "PAN is not well formated!";
				logger.info(DECLINE + errorMsg);
				result.setErrorMsg(errorMsg);
				return new ResponseEntity<PccResponseDTO>(result, HttpStatus.BAD_REQUEST);
			}

			String iin = dto.getPan().substring(0, 6);
			logger.info("iin:"+  iin);
			
			Bank issuer = null;
			
			
			ArrayList<Bank> banks = (ArrayList<Bank>) bankService.findAll();
			for(Bank bank : banks){
				if(bank.getIin().equals(iin)){
					issuer = bank;
					break;
				}
			}
			
//			try{
//				issuer = bankService.findByIin(iin);
//
//			}catch(NullPointerException ex){
//				
//			}
			
			logger.info("test3");
			if(issuer == null){
				errorMsg = "Issuer does not exist!";
				logger.info(DECLINE + errorMsg);
				result.setErrorMsg(errorMsg);
				return new ResponseEntity<PccResponseDTO>(result, HttpStatus.BAD_REQUEST);
			}		

			tr = new Transaction(dto);
			tr.setAcquirer(acquirer);
			tr.setIssuer(issuer);
			
			tr = transactionService.save(tr);
			logger.info("Entity saved!");
			
//			String requestUrl = "http://localhost:8083/Issuer/order";
			IssuerRequestDTO issuerRequest = new IssuerRequestDTO(tr);
			ResponseEntity<String> response = restTemp.postForEntity(issuer.getUrl(), mapper.writeValueAsString(issuerRequest), String.class);
					
			
			if(response.getStatusCode() == HttpStatus.OK){
				
				IssuerResponseDTO responseDto = mapper.readValue(response.getBody(), IssuerResponseDTO.class);
				logger.info("PCC received Issuer's response");
								
				tr.setIssuerOrderId(responseDto.getIssuerOrderId());
				tr.setIssuerTimestamp(responseDto.getIssuerOrderTimestamp());
				tr.setOrderState(responseDto.getState());				
				tr = transactionService.save(tr);
				
				result = new PccResponseDTO(tr);
				return new ResponseEntity<PccResponseDTO>(result, HeaderUtil.getHeader(), HttpStatus.OK);
				
			}else{	
				tr.setOrderState(OrderStateEnum.UNSUCCESSFULL);
				tr = transactionService.save(tr);
				
//				result = new PccResponseDTO(tr);
				result.setErrorMsg(response.getBody());
				logger.error("PCC received error - HTTP State: " + response.getStatusCode());
				return new ResponseEntity<Object>(result, response.getStatusCode());
			}
			
		}catch(Exception ex){
			logger.error(ex.toString());

			if(tr != null){
				tr.setOrderState(OrderStateEnum.UNSUCCESSFULL);
				tr = transactionService.save(tr);
			}
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
  }	

}
