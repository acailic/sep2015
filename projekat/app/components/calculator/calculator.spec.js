describe("CalculatorController", function() {
	var calcCtrl, sharedObject, Insurance, $state, $httpBackend, $scope;
	beforeEach(module("merchant"));
	//pre svakog testa učitavamo app modul
	beforeEach(module("merchant.calculator"));
	//pre svakog testa učitavamo mock employeeService servis sa datom implementacijom
	beforeEach(module(function($provide){
		$provide.factory('sharedObject', function(){
			return {
          		setInsurance: function(){ return true;},
          		getInsurance: function(){return true;}
			};
		});
	}));

	//pre svakog testa "ubrizgavamo" $controller i mock employeeService i definišemo kontroler
	beforeEach(inject(function($controller, _sharedObject_, _Insurance_, _$state_, _$httpBackend_) {
		sharedObject = _sharedObject_;
		Insurance = _Insurance_;
		$state = _$state_;
		$httpBackend = _$httpBackend_;
		$scope = {};
		$httpBackend.whenGET("app/components/core/header.html").respond("<div/>");
        $httpBackend.whenGET("app/components/core/menu.html").respond("<div/>");
        $httpBackend.whenGET("app/components/sale/sale.html").respond("<div/>");
        $httpBackend.whenGET("app/components/insurance/insurance.html").respond("<div/>");
        $httpBackend.whenGET('https://localhost:8444/spring4/data_init').respond(200, [1]);

       	
		calcCtrl = $controller("CalculatorController", {
			sharedObject : sharedObject,
			Insurance : Insurance,
			$state : $state,
			$httpBackend: $httpBackend,
			$scope: $scope
		});
	}));




	it("Variables and functions to be defined", function() {
		$httpBackend.flush();
	 	expect(calcCtrl.price).toBeDefined();
	 	expect(calcCtrl.insurance).toBeDefined();
		expect(calcCtrl.age_number).toBeDefined();
		expect(calcCtrl.someSelected).toBeDefined();
		expect(calcCtrl.resetTravel).toBeDefined();
		expect(calcCtrl.resetHome).toBeDefined();
		expect(calcCtrl.resetVehicle).toBeDefined();
		expect(calcCtrl.calculateTravel).toBeDefined();
		expect(calcCtrl.calculateHome).toBeDefined();
		expect(calcCtrl.calculateVehicle).toBeDefined();
		expect(calcCtrl.validateVehicle).toBeDefined();
		expect(calcCtrl.sale).toBeDefined();

		
	});


	

	it("Should get Initial data", inject(function ($rootScope) {
	    $httpBackend.expectGET('https://localhost:8444/spring4/data_init')
         .respond(200, [1]);
        Insurance.data_init();
        $rootScope.$digest();
		$httpBackend.flush();
      	expect(calcCtrl.data_init).toBeDefined();
	}));

	it("Should calculate the price of Travel insurance", inject(function ($rootScope) {
		expect(calcCtrl.calculateTravel).toBeDefined();
		var travel = "travel";
		$httpBackend.expectPOST('https://localhost:8444/spring4/calculateTravel', travel).respond(200, "travel");
		Insurance.calculateTravel(travel);
		$httpBackend.flush();
       	
	}));

	it("Should calculate the price of Home insurance", inject(function ($rootScope) {
		expect(calcCtrl.calculateHome).toBeDefined();
		var home = "home";
		$httpBackend.expectPOST('https://localhost:8444/spring4/calculateHome', home).respond(200, "home");
		Insurance.calculateHome(home);
		$httpBackend.flush();
       	
	}));

	it("Should calculate the price of Vehicle insurance", inject(function ($rootScope) {
		expect(calcCtrl.calculateVehicle).toBeDefined();
		var vehicle = "vehicle";
		$httpBackend.expectPOST('https://localhost:8444/spring4/calculateVehicle', vehicle).respond(200, "vehicle");
		Insurance.calculateVehicle(vehicle);
		$httpBackend.flush();
       	
	}));


	afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
	});


	
	

});
