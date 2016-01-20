(function() {
	"use strict";
	
	var crTranslations = {
		"en": {
			"LABELS": {
				"VALUTA":"USD$",
				"HOME":"Home",
				"INPUT":"Input",
				"ABOUT":"About",
				"PAYMENT":"Payment",
				"ORDERSUMMARY":"Order summary:",
				"ACTIONBUT":"Action button",
				"ORDERTOTAL":"Order total:",
				"BUYERPROTECTION":"Buyer protection by ",
				"PAYWITHCARD":"Pay with card",
				"REQUIRED":"This is required",
				"SECCODE":"Security code",
				"EXPMONTH":"Expiration month",
				"EXPYEAR":"Expiration year",
				"SUBPAYMENT":"Submit Payment ",
				"DECPAYMENT":"Decline Payment",
				"CANCELANDRETURN":"Cancel and return to Merchant site",
				"CARDTYPE":"Card Type",
				"CARDNUM":"Card Number",
				"CARDHOLDERNAME":"Card name", 
				"CARDHOLDERLASTNAME":"Cardholder lastname",   
				"ORDERSUMMARYTEXT":"This is summary of order. Fill out the payment form.",
				"LETTERS":"This field should contain letters.", 
				"CARDNUMM":"Card number has to be at lest 8 digits long.",
				"SECCODEM":"Security code has to be at lest 4 digits long.",
				"EXPMONTHM":"Write a month when your card expire.",
				"EXPYEARM":"Write a year when your card expire." 
					
			},

			"NOTE":"Note:Your payment is secured with VeriSign SSL encryption, the highest commercially available encryption technology. Please be assured that your credit/debit card details will not be exposed.Import duties, taxes and other customs related charges are not included. Buyers bear all responsibility for all extra charges incurred (if any).",
			"LANGUAGE": "English"
		},
		"sr-latn": {
			 
			"LABELS": {
				"VALUTA":"din",
				"HOME":"Pocetna strana",
				"INPUT":"Unos",
				"ABOUT":"O nama",
				"PAYMENT":"Placanje",
				"ACTIONBUT":"Akcija dugme",
				"ORDERTOTAL":"Ukupna cena:",
				"ORDERSUMMARY":"Detalji transakcije:",
				"BUYERPROTECTION":"Zastita potrosaca od strane", 
				"PAYWITHCARD":"Plaćanje karticom",
				"REQUIRED":"Ovo je potrebno",
				"SECCODE":"Sigurnosni kod",
				"EXPMONTH":"Mesec kada kartica istice",
				"EXPYEAR":"Godina kada kartica istice",
				"SUBPAYMENT":"Potvrda placanja",
				"DECPAYMENT":"Odustani",
				"CANCELANDRETURN":"Odustani i vrati se na sajt prodavca",
				"CARDTYPE":"Tip kartice",
				"CARDNUM":"Broj kartice", 
				"CARDHOLDERNAME":"Ime nosioca kartice", 
				"CARDHOLDERLASTNAME":"Prezime nosioca kartice",   
				"ORDERSUMMARYTEXT":"Ovo je rezime transakcije. Popunite formu placanja.", 
				"LETTERS":"Ovo polje treba da sadrzi samo slova.",
				"CARDNUMM":"Broj kartice se sastoji od 8 cifara.",
				"SECCODEM":"Sigurnosni kod se sastoji od 4 cifre.",
				"EXPMONTHM":"Napisite mesec kada kartice istice.",
				"EXPYEARM":"Napisite godinu isticanja kartice." 	
			},
 			
 			"NOTE":"Note:",
			
			"LANGUAGE": "Srpski"
		},
	};

	angular
		.module('acquirer.i18n.constants')
		.constant("crTranslations", crTranslations);
})();