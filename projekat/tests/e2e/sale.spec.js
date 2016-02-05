describe("Sale", function () {

    beforeEach(function () {
        browser.get("https://localhost:8001/#/saleWizard1");
    });

    //Test za koji su svi podaci validni
    it("should fill sale wizard with valid data", function () {
        expect(browser.getTitle()).toBe("Merchant");
        
        expect(element(by.id("saleForm"))).toBeDefined(true);
        var regionId = element(by.model("slc.insurance.travel.region_id"));
        regionId.sendKeys("Azija");
        var numberOfPeople = element(by.model("slc.number_of_people"));
        numberOfPeople.sendKeys("1");
        var maxValueId = element(by.model("slc.insurance.travel.max_value_id"));
        maxValueId.sendKeys("10.000EUR");
        var travelOwnerFirstName = element(by.model("slc.insurance.travel.owner.first_name"));
        travelOwnerFirstName.sendKeys("Danijela");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.last_name"));
        travelOwnerLastName.sendKeys("Petrovic");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.email"));
        travelOwnerLastName.sendKeys("danijelaaa@gmail.com");
        element(by.id("buttonPart1")).click();

        expect(element(by.id("saleForm2"))).toBeDefined(true);
        var travellerFirstName = element(by.model("slc.traveller.first_name"));
        travellerFirstName.sendKeys("Danijela");
        var travellerLastName = element(by.model("slc.traveller.last_name"));
        travellerLastName.sendKeys("Petrovic");
        var humanAge = element(by.model("slc.traveller.human_age_id"));
        humanAge.sendKeys("Do18");
        var travellerJmbg = element(by.model("slc.traveller.jmbg"));
        travellerJmbg.sendKeys("1234567897894");
        var travellerPassport = element(by.model("slc.traveller.passport_num"));
        travellerPassport.sendKeys("789456123");
        var travellerTelNum = element(by.model("slc.traveller.tel_num"));
        travellerTelNum.sendKeys("0632154789");
        var travellerAddress = element(by.model("slc.traveller.address"));
        travellerAddress.sendKeys("Dositejeva 7");
        var travellerCity = element(by.model("slc.traveller.city_id"));
        travellerCity.sendKeys("Beograd");

        element(by.id("buttonPart2")).click();
        element(by.id("buttonPart3")).click();

        //provera da li je iznos putnog osiguranja definisan i razlicit od 0
        expect(by.model("slc.price.travel_price")).toBeDefined(true);
        expect(by.model("slc.price.travel_price")).not.toEqual(0);
        //provera da li je suma svih osiguranja definisan i razlicit od 0
        expect(by.model("slc.price.sum_price")).toBeDefined(true);
        expect(by.model("slc.price.travel_price")).not.toEqual(0);
        element(by.id("buttonPart4")).click();

        expect(browser.getCurrentUrl()).not.toContain('saleWizard1');
    });

    //Test za koji nije unet obavezan podatak (ime putnika) - ne dozvoljava se nastavak prolaska kroz wizard
    it("should not fill all required data in sale wizard", function () {
        expect(browser.getTitle()).toBe("Merchant");
       
        expect(element(by.id("saleForm"))).toBeDefined(true);
        var regionId = element(by.model("slc.insurance.travel.region_id"));
        regionId.sendKeys("Azija");
        var numberOfPeople = element(by.model("slc.number_of_people"));
        numberOfPeople.sendKeys("1");
        var maxValueId = element(by.model("slc.insurance.travel.max_value_id"));
        maxValueId.sendKeys("10.000EUR");
        var travelOwnerFirstName = element(by.model("slc.insurance.travel.owner.first_name"));
        travelOwnerFirstName.sendKeys("");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.last_name"));
        travelOwnerLastName.sendKeys("Petrovic");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.email"));
        travelOwnerLastName.sendKeys("danijelaaa@gmail.com");
        element(by.model("slc.activity")).click();
        var sport = element(by.model("slc.insurance.travel.sport_id"));
        sport.sendKeys("fudbal");

        element(by.id("buttonPart1")).click();

        var myElement = element(by.id('buttonPart2'));
        expect(myElement.isPresent()).toBeFalsy();
    });
    

    //Test za gde su obrisani podaci iz tabele o putniku i ne dozvoljava se prelazak na sledeci tab u wizardu dok se ponovo ne unesu valdini podaci, dugme je disable-ovano
    it("should delete traveller data then add again traveller data in sale wizard", function () {
        expect(browser.getTitle()).toBe("Merchant");
        
        expect(element(by.id("saleForm"))).toBeDefined(true);
        var regionId = element(by.model("slc.insurance.travel.region_id"));
        regionId.sendKeys("Azija");
        var numberOfPeople = element(by.model("slc.number_of_people"));
        numberOfPeople.sendKeys("1");
        var maxValueId = element(by.model("slc.insurance.travel.max_value_id"));
        maxValueId.sendKeys("10.000EUR");
        var travelOwnerFirstName = element(by.model("slc.insurance.travel.owner.first_name"));
        travelOwnerFirstName.sendKeys("Danijela");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.last_name"));
        travelOwnerLastName.sendKeys("Petrovic");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.email"));
        travelOwnerLastName.sendKeys("danijelaaa@gmail.com");
        element(by.id("buttonPart1")).click();

        expect(element(by.id("saleForm2"))).toBeDefined(true);
        var travellerFirstName = element(by.model("slc.traveller.first_name"));
        travellerFirstName.sendKeys("Danijela");
        var travellerLastName = element(by.model("slc.traveller.last_name"));
        travellerLastName.sendKeys("Petrovic");
        var humanAge = element(by.model("slc.traveller.human_age_id"));
        humanAge.sendKeys("Do18");
        var travellerJmbg = element(by.model("slc.traveller.jmbg"));
        travellerJmbg.sendKeys("1234567897894");
        var travellerPassport = element(by.model("slc.traveller.passport_num"));
        travellerPassport.sendKeys("789456123");
        var travellerTelNum = element(by.model("slc.traveller.tel_num"));
        travellerTelNum.sendKeys("0632154789");
        var travellerAddress = element(by.model("slc.traveller.address"));
        travellerAddress.sendKeys("Dositejeva 7");
        var travellerCity = element(by.model("slc.traveller.city_id"));
        travellerCity.sendKeys("Beograd");

        element(by.id("buttonPart2")).click();

        //Brisanje putnika iz tabele
        element(by.id("deleteTraveller1")).click();
        element(by.id("buttonPart2")).click();

        var submit = element(by.id("buttonPart3"));
        expect(submit.isEnabled()).toBe(false);

        //ponovno popunjavanje forme za podatke putnika
        var travellerFirstName = element(by.model("slc.traveller.first_name"));
        travellerFirstName.sendKeys("Igor");
        var travellerLastName = element(by.model("slc.traveller.last_name"));
        travellerLastName.sendKeys("Jovin");
        var humanAge = element(by.model("slc.traveller.human_age_id"));
        humanAge.sendKeys("18-60");
        var travellerJmbg = element(by.model("slc.traveller.jmbg"));
        travellerJmbg.sendKeys("1234567897894");
        var travellerPassport = element(by.model("slc.traveller.passport_num"));
        travellerPassport.sendKeys("789456123");
        var travellerTelNum = element(by.model("slc.traveller.tel_num"));
        travellerTelNum.sendKeys("0632154789");
        var travellerAddress = element(by.model("slc.traveller.address"));
        travellerAddress.sendKeys("Bul. Osl. 55");
        var travellerCity = element(by.model("slc.traveller.city_id"));
        travellerCity.sendKeys("Subotica");
        element(by.id("buttonPart2")).click();

        expect(submit.isEnabled()).toBe(true);
        element(by.id("buttonPart3")).click();
        //provera da li je iznos putnog osiguranja definisan i razlicit od 0
        expect(by.model("slc.price.travel_price")).toBeDefined(true);
        expect(by.model("slc.price.travel_price")).not.toEqual(0);
        //provera da li je suma svih osiguranja definisana i razlicita od 0
        expect(by.model("slc.price.sum_price")).toBeDefined(true);
        expect(by.model("slc.price.travel_price")).not.toEqual(0);
        element(by.id("buttonPart4")).click();

        expect(browser.getCurrentUrl()).not.toContain('saleWizard1');

    });

    
    //Test za koji nisu svi podaci o putniku validni pa je dugme za prelazak sa drugog na treci tab disable-ovan
    it("should fill sale wizard with not valid data", function () {
        expect(browser.getTitle()).toBe("Merchant");
       
        expect(element(by.id("saleForm"))).toBeDefined(true);
        var regionId = element(by.model("slc.insurance.travel.region_id"));
        regionId.sendKeys("Azija");
        var numberOfPeople = element(by.model("slc.number_of_people"));
        numberOfPeople.sendKeys("1");
        var maxValueId = element(by.model("slc.insurance.travel.max_value_id"));
        maxValueId.sendKeys("10.000EUR");
        var travelOwnerFirstName = element(by.model("slc.insurance.travel.owner.first_name"));
        travelOwnerFirstName.sendKeys("Danijela");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.last_name"));
        travelOwnerLastName.sendKeys("Petrovic");
        var travelOwnerLastName = element(by.model("slc.insurance.travel.owner.email"));
        travelOwnerLastName.sendKeys("danijelaaa@gmail.com");
        element(by.id("buttonPart1")).click();

        expect(element(by.id("saleForm2"))).toBeDefined(true);
        var travellerFirstName = element(by.model("slc.traveller.first_name"));
        travellerFirstName.sendKeys("Danijela");
        var travellerLastName = element(by.model("slc.traveller.last_name"));
        travellerLastName.sendKeys("Petrovic");
        var humanAge = element(by.model("slc.traveller.human_age_id"));
        humanAge.sendKeys("Do18");
        var travellerJmbg = element(by.model("slc.traveller.jmbg"));
        travellerJmbg.sendKeys("123");
        var travellerPassport = element(by.model("slc.traveller.passport_num"));
        travellerPassport.sendKeys("11");
        var travellerTelNum = element(by.model("slc.traveller.tel_num"));
        travellerTelNum.sendKeys("0632154789");
        var travellerAddress = element(by.model("slc.traveller.address"));
        travellerAddress.sendKeys("Dositejeva 7");
        var travellerCity = element(by.model("slc.traveller.city_id"));
        travellerCity.sendKeys("Beograd");

        element(by.id("buttonPart2")).click();

        var submit = element(by.id("buttonPart3"));
        expect(submit.isEnabled()).toBe(false);
    });

    

});