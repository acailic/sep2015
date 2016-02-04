describe("Input Page", function () {

    /// KOMANDA ZA POKRETANJE NA PORTU 8000

    ///http-server -a localhost -p 8000

    /*onPrepare: function () {
    global.EC = protractor.ExpectedConditions;

    },*/
    //pre svakog testa učitamo url koji je definisan kao baseUrl + parametar browser.get metode (u ovom slučaju samo baseUrl)
    
    beforeEach(function () {
        browser.get("#/input/idpayment/1");
    });

    //proveravamo da li je učitana startna stranica
    it("should load the start page.", function () {
        expect(browser.getTitle()).toBe("Acquirer");
        expect(element(by.id("paymentForm"))).toBeDefined(true);
    });


    it("should generate an transaction.", function () {

        var cardholderName = element(by.model("inc.transaction.cardholdername"));
        cardholderName.sendKeys("stevan");
        var cardholderlastname = element(by.model("inc.transaction.cardholderlastname"));
        cardholderlastname.sendKeys("stevic");
        var cardtype = element(by.model("inc.transaction.cardtype"));
        cardtype.sendKeys("Master");
        var pan = element(by.model("inc.transaction.pan"));
        pan.sendKeys("12341234");
        var cardSecCode = element(by.model("inc.transaction.cardSecCode"));
        cardSecCode.sendKeys("1234");
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
    });
 
});