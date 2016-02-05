describe("Calculator ", function () {

	beforeEach(function () {
       browser.get("https://localhost:8001/#/calculator");
       
    });

      //test putnog osiguranja bez dodatnih paketa sa validnim podacima
     it("should calculate travel price and redirect to saleWizard1", function () {

        expect(browser.getTitle()).toBe("Merchant");
        expect(element(by.id("travelForm"))).toBeDefined(true);
      
        
        var durationInput = element(by.model("calc.insurance.travel.duration"));
        durationInput.sendKeys("2");

      
        var regionSelect = element(by.model("calc.insurance.travel.region_id"));
       regionSelect.sendKeys("Azija");


        var humanAgeNumberSelect = element(by.model("calc.age_number.number18"));
        humanAgeNumberSelect.sendKeys("1");

        var humanAgeNumberSelect = element(by.model("calc.age_number.number18_60"));
        humanAgeNumberSelect.sendKeys("1");


        var maxValueSelect = element(by.model("calc.insurance.travel.max_value_id"));
         maxValueSelect.sendKeys("2");



       element(by.model("activity")).click();


       var sportSelect = element(by.model("calc.insurance.travel.sport_id"));
       sportSelect.sendKeys("fudbal");

       
        var buttonTravel = element(by.id("calculateTravel"));
        buttonTravel.click();
       

        
       

       var buttonSale = element(by.id("sale"));
       buttonSale.click();

       expect(browser.getCurrentUrl()).toContain('saleWizard1');

      
      

    });

      
     //test putnog osiguranja bez dodatnih paketa sa nepotpunim podacima, nije izabran region, nece izvrsiti izracunavanje i redirekciju
     it("should not calculate travel price and redirect to saleWizard1", function () {

        expect(browser.getTitle()).toBe("Merchant");
        expect(element(by.id("travelForm"))).toBeDefined(true);
      
        
        var durationInput = element(by.model("calc.insurance.travel.duration"));
        durationInput.sendKeys("2");


        var humanAgeNumberSelect = element(by.model("calc.age_number.number18"));
        humanAgeNumberSelect.sendKeys("1");

        var humanAgeNumberSelect = element(by.model("calc.age_number.number18_60"));
        humanAgeNumberSelect.sendKeys("1");


        var maxValueSelect = element(by.model("calc.insurance.travel.max_value_id"));
         maxValueSelect.sendKeys("2");



       element(by.model("activity")).click();


       var sportSelect = element(by.model("calc.insurance.travel.sport_id"));
       sportSelect.sendKeys("fudbal");

       
        var buttonTravel = element(by.id("calculateTravel"));
        buttonTravel.click();
       
     

       var buttonSale = element(by.id("sale"));
       buttonSale.click();

       expect(browser.getCurrentUrl()).not.toContain('saleWizard1');

      
      

    });

 
    //test sa validnim podacima putog osiguranja sa dodatnim paketima
     it("should calculate travel, home and vehicle price , and redirect to saleWizard1", function () {

     	expect(browser.getTitle()).toBe("Merchant");
        expect(element(by.id("travelForm"))).toBeDefined(true);
    	
        
        var durationInput = element(by.model("calc.insurance.travel.duration"));
        durationInput.sendKeys("2");

      
        var regionSelect = element(by.model("calc.insurance.travel.region_id"));
       regionSelect.sendKeys("Azija");


        var humanAgeNumberSelect = element(by.model("calc.age_number.number18"));
        humanAgeNumberSelect.sendKeys("1");

        var humanAgeNumberSelect = element(by.model("calc.age_number.number18_60"));
        humanAgeNumberSelect.sendKeys("1");


        var maxValueSelect = element(by.model("calc.insurance.travel.max_value_id"));
	   	   maxValueSelect.sendKeys("2");



	   	 element(by.model("activity")).click();


	     var sportSelect = element(by.model("calc.insurance.travel.sport_id"));
       sportSelect.sendKeys("fudbal");

       
        var buttonTravel = element(by.id("calculateTravel"));
        buttonTravel.click();
       

        
        element(by.model("calc.flat")).click();

        element(by.model("calc.car")).click();

        
        var durationInput = element(by.model("calc.insurance.home.duration"));
        durationInput.sendKeys("1");

        element(by.model("calc.insurance.home.floor_area")).sendKeys("60");

        element(by.model("calc.insurance.home.flat_age")).sendKeys("5.0");

        element(by.model("calc.insurance.home.est_value")).sendKeys("40000");

      

        element.all(by.id("casualty")).click();

      
        var buttonHome = element(by.id("calculateHome"));
        buttonHome.click();
        

        


       element(by.model("calc.insurance.vehicle.duration")).sendKeys("3");
       element(by.model("calc.towing")).click();


       var towingSelect = element(by.model("calc.insurance.vehicle.towing_id"));
       towingSelect.sendKeys("Towing1");


     

       
       var buttonVehicle = element(by.id("calculateVehicle"));
       buttonVehicle.click();

       


       var buttonSale = element(by.id("sale"));
       buttonSale.click();

       expect(browser.getCurrentUrl()).toContain('saleWizard1');

       
      
    });


      //u okviru forme Pomoc na putu nije izabran tip slepovanja, zbog toga se nece izracunati cijena osiguranja pomoci na putu
      // niti dozvoliti prelazak na kupovinu osiguranja
      it("should calculate travel and home price, shouldn't calculate vehicle price and redirect to saleWizard1", function () {

          expect(browser.getTitle()).toBe("Merchant");
        expect(element(by.id("travelForm"))).toBeDefined(true);
      
        
        var durationInput = element(by.model("calc.insurance.travel.duration"));
        durationInput.sendKeys("2");

      
        var regionSelect = element(by.model("calc.insurance.travel.region_id"));
       regionSelect.sendKeys("Azija");


        var humanAgeNumberSelect = element(by.model("calc.age_number.number18"));
        humanAgeNumberSelect.sendKeys("1");

        var humanAgeNumberSelect = element(by.model("calc.age_number.number18_60"));
        humanAgeNumberSelect.sendKeys("1");


        var maxValueSelect = element(by.model("calc.insurance.travel.max_value_id"));
         maxValueSelect.sendKeys("2");



       element(by.model("activity")).click();


       var sportSelect = element(by.model("calc.insurance.travel.sport_id"));
       sportSelect.sendKeys("fudbal");

       
        var buttonTravel = element(by.id("calculateTravel"));
        buttonTravel.click();
       

        
        element(by.model("calc.flat")).click();

        element(by.model("calc.car")).click();

        
        var durationInput = element(by.model("calc.insurance.home.duration"));
        durationInput.sendKeys("1");

        element(by.model("calc.insurance.home.floor_area")).sendKeys("60");

        element(by.model("calc.insurance.home.flat_age")).sendKeys("5.0");

        element(by.model("calc.insurance.home.est_value")).sendKeys("40000");

      

        element.all(by.id("casualty")).click();

      
        var buttonHome = element(by.id("calculateHome"));
        buttonHome.click();
        

        


       element(by.model("calc.insurance.vehicle.duration")).sendKeys("3");
       element(by.model("calc.towing")).click();
  
      
       
       var buttonVehicle = element(by.id("calculateVehicle"));
       buttonVehicle.click();

       


       var buttonSale = element(by.id("sale"));
       buttonSale.click();

       expect(browser.getCurrentUrl()).not.toContain('saleWizard1');

    	
      

    });
  



 //test sa nevalidnim podacima, u polje starost stana je unesen nevalidan decimalni broj, validacija ne dozvoljava izracunavanje cijene stana niti redirekciju na kupovinu 
     it("should calculate travel and vehicle price, should not calculate home price, should not redirect to saleWizard1", function () {

      expect(browser.getTitle()).toBe("Merchant");
        expect(element(by.id("travelForm"))).toBeDefined(true);
      
        
        var durationInput = element(by.model("calc.insurance.travel.duration"));
        durationInput.sendKeys("2");

      
        var regionSelect = element(by.model("calc.insurance.travel.region_id"));
       regionSelect.sendKeys("Azija");


        var humanAgeNumberSelect = element(by.model("calc.age_number.number18"));
        humanAgeNumberSelect.sendKeys("1");

        var humanAgeNumberSelect = element(by.model("calc.age_number.number18_60"));
        humanAgeNumberSelect.sendKeys("1");


        var maxValueSelect = element(by.model("calc.insurance.travel.max_value_id"));
         maxValueSelect.sendKeys("2");



       element(by.model("activity")).click();


       var sportSelect = element(by.model("calc.insurance.travel.sport_id"));
       sportSelect.sendKeys("fudbal");

       
        var buttonTravel = element(by.id("calculateTravel"));
        buttonTravel.click();
       

        
        element(by.model("calc.flat")).click();

        element(by.model("calc.car")).click();

        
        var durationInput = element(by.model("calc.insurance.home.duration"));
        durationInput.sendKeys("1");

        element(by.model("calc.insurance.home.floor_area")).sendKeys("60");

        element(by.model("calc.insurance.home.flat_age")).sendKeys("5....");

        element(by.model("calc.insurance.home.est_value")).sendKeys("40000");

      

        element.all(by.id("casualty")).click();

      
        var buttonHome = element(by.id("calculateHome"));
        buttonHome.click();
        

        


       element(by.model("calc.insurance.vehicle.duration")).sendKeys("3");
       element(by.model("calc.towing")).click();


       var towingSelect = element(by.model("calc.insurance.vehicle.towing_id"));
       towingSelect.sendKeys("Towing1");


     

       
       var buttonVehicle = element(by.id("calculateVehicle"));
       buttonVehicle.click();

       


       var buttonSale = element(by.id("sale"));
       buttonSale.click();

       expect(browser.getCurrentUrl()).not.toContain('saleWizard1');

       
      
    });








});