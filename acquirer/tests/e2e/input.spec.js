describe("Input Page", function () {

    /// KOMANDA ZA POKRETANJE NA PORTU 8000

    ///http-server -a localhost -p 8000

    /*onPrepare: function () {
    global.EC = protractor.ExpectedConditions;

    },*/
    //pre svakog testa učitamo url koji je definisan kao baseUrl + parametar browser.get metode (u ovom slučaju samo baseUrl)
    
    beforeEach(function () {
        browser.get("#/input");
    });

    //proveravamo da li je učitana startna stranica
    it("should load the start page.", function () {
        expect(browser.getTitle()).toBe("Acquirer");
        expect(element(by.id("paymentForm"))).toBeDefined(true);
    });


    it("should try to generate an transaction  successfuly.", function () {
         browser.get("#/input/idpayment/1");
        var cardholderName = element(by.model("inc.transaction.cardholdername"));
        cardholderName.sendKeys("stevan");
        var cardholderlastname = element(by.model("inc.transaction.cardholderlastname"));
        cardholderlastname.sendKeys("stevic");
        var cardtype = element(by.model("inc.transaction.cardtype"));
        cardtype.sendKeys("Master");
        var pan = element(by.model("inc.transaction.pan"));
        pan.sendKeys("1234567891012345");
        var cardSecCode = element(by.model("inc.transaction.cardSecCode"));
        cardSecCode.sendKeys("123");
        var expmonth = element(by.model("inc.transaction.expmonth"));
        expmonth.sendKeys("12");
        var expyear = element(by.model("inc.transaction.expyear"));
        expyear.sendKeys("2016");

        var generate = element(by.model("submit"));
        generate.click();

        var EC = protractor.ExpectedConditions;
        dialog = element(by.css('md-dialog-content'));  // or just $('md-dialog-content')

        //testiram pozivanje generisanja transakcije
        //i da dobijem odgovor
        browser.wait(EC.presenceOf(dialog), 3500);
        expect(dialog.isPresent()).toBe(true);
        //var gotomerchant  = element(by.css('md-dialog-content')).element(by.css('md-button'));  
         var gotomerchant  = element(by.css('md-dialog-content')).element(by.buttonText("Go to merchant")) ;  
        gotomerchant.click();
         
        expect(browser.getCurrentUrl()).not.toContain('input');
         

    });

     it("should try to generate an transaction and fail. MISING VALUES OF INPUT LINK", function () {
        
        browser.get("#/input/idpayment/");
        var cardholderName = element(by.model("inc.transaction.cardholdername"));
        cardholderName.sendKeys("Stevan,.-.,-.,");
        var cardholderlastname = element(by.model("inc.transaction.cardholderlastname"));
        cardholderlastname.sendKeys("Stevic123");
        var cardtype = element(by.model("inc.transaction.cardtype"));
        cardtype.sendKeys("Master2");
        var pan = element(by.model("inc.transaction.pan"));
        pan.sendKeys("12345678910123451");
        var cardSecCode = element(by.model("inc.transaction.cardSecCode"));
        cardSecCode.sendKeys("1123");
        var expmonth = element(by.model("inc.transaction.expmonth"));
        expmonth.sendKeys("123");
        var expyear = element(by.model("inc.transaction.expyear"));
        expyear.sendKeys("20173");

        var generate = element(by.model("submit"));
        generate.click();

        cardholderName.clear();
        cardholderName.sendKeys("Stevan");
        cardholderlastname.clear();
        cardholderlastname.sendKeys("Stevic");
        cardtype.clear();
        cardtype.sendKeys("Master");
        pan.clear();
        pan.sendKeys("1234567891012345");
        cardSecCode.clear();
        cardSecCode.sendKeys("123");
        expmonth.clear();
        expmonth.sendKeys("12");
        expyear.clear();
        expyear.sendKeys("2017");


        generate.click();


        var EC = protractor.ExpectedConditions;
        dialog = element(by.css('md-dialog-content'));  // or just $('md-dialog-content')

        //testiram pozivanje generisanja transakcije
        //i da dobijem odgovor
       // expect(browser.wait(EC.presenceOf(dialog), 3000)).toBe(false);
        browser.wait(EC.presenceOf(dialog), 3000);
        expect(dialog.isPresent()).toBe(true);
       // browser.switchTo().alert().accept();
    }); 



 
});