describe("SaleController", function() {
	var slcCtrl, Insurance, SharedObject, $scope, $state, $location, $window, $httpBackend, $rootScope;
	
	beforeEach(module("merchant"));
	beforeEach(module("merchant.sale"));

	beforeEach(inject(function($controller, _SharedObject_, _Insurance_, _$state_, _$location_, _$window_, _$httpBackend_, _$rootScope_) {
		SharedObject = _SharedObject_;
		Insurance = _Insurance_;
		$state = _$state_;
		$location = _$location_;
		$window = _$window_;
		$httpBackend = _$httpBackend_;
		$rootScope = _$rootScope_;
		$scope = $rootScope.$new();
		$httpBackend.whenGET("app/components/core/header.html").respond("<div/>");
        $httpBackend.whenGET("app/components/core/menu.html").respond("<div/>");
        $httpBackend.whenGET("app/components/sale/sale.html").respond("<div/>");
        $httpBackend.whenGET("app/components/insurance/insurance.html").respond("<div/>");
        $httpBackend.whenGET('http://localhost:8444/spring4/data_init').respond(200, [1]);
		slcCtrl = $controller("SaleController", {
			SharedObject : SharedObject,
			Insurance : Insurance,
			$state : $state,
			$location : $location,
			$window : $window,
			$httpBackend : $httpBackend,
			$rootScope : $rootScope,
			$scope : $scope
		});
	}));

	it("Variables to be defined", function() {
		$httpBackend.flush();
	 	expect(slcCtrl.insurance).toBeDefined();
	 	expect(slcCtrl.insurance.travellers).toEqual([]);
	 	expect(slcCtrl.finalView).toBeDefined();
	 	expect(slcCtrl.addTravellerForm).toBeDefined();
	 	expect(slcCtrl.checkTravellers).toBeDefined();
	 	expect(slcCtrl.number_of_travellers).toEqual(0);
	 	expect(slcCtrl.travelStartDateMin).toBeDefined();
		expect(slcCtrl.selectedIndex).toEqual(0);
	 	expect(slcCtrl.dates).toEqual([]);
	 });
	
	it("Methods to be defined", function() {
		$httpBackend.flush();	
		expect(slcCtrl.getInsurancesDetails).toBeDefined();
		expect(slcCtrl.getTravellersDetails).toBeDefined();
		expect(slcCtrl.deleteTraveller).toBeDefined();
		expect(slcCtrl.editTraveller).toBeDefined();
		expect(slcCtrl.setDates).toBeDefined();
		expect(slcCtrl.setCausualties).toBeDefined();
		expect(slcCtrl.setRisks).toBeDefined();
		expect(slcCtrl.setRegion).toBeDefined();	
		expect(slcCtrl.setMaxValue).toBeDefined();
		expect(slcCtrl.calculate).toBeDefined();
		expect(slcCtrl.formatDate).toBeDefined();
		expect(slcCtrl.createInsurance).toBeDefined();
		expect(slcCtrl.someSelected).toBeDefined();
		expect(slcCtrl.resetDatePickers).toBeDefined();
		expect(slcCtrl.checkFlat).toBeDefined();
		expect(slcCtrl.checkCar).toBeDefined();
	 });

    it("Should have insurance object be defined when state is main.sale.wizard1", inject(function ($rootScope) {
        $httpBackend.whenGET("app/components/core/header.html").respond("<div/>");
        $httpBackend.whenGET("app/components/core/menu.html").respond("<div/>");
        $httpBackend.whenGET("app/components/sale/sale.html").respond("<div/>");
        $httpBackend.whenGET("app/components/insurance/insurance.html").respond("<div/>");
        $httpBackend.whenGET("app/components/sale/sale-wizard-part1.html").respond("<div/>");

        expect(SharedObject.getInsurance()).toEqual({});
        SharedObject.setInsurance("insurance");

        $httpBackend.flush();
        $rootScope.$digest();
        $state.go('main.sale.wizard1');
        $httpBackend.flush();
        $rootScope.$digest();

        expect($state.current.name).toBe("main.sale.wizard1");

		var insurance = SharedObject.getInsurance();
		expect(insurance).toEqual("insurance");
    }));

    it("Should get Initial data", inject(function () {
	    $httpBackend.expectGET('https://localhost:8444/spring4/data_init')
         .respond(200, [1]);
        Insurance.data_init();
		$httpBackend.flush();
      	expect(slcCtrl.data_init).toBeDefined();
	}));

	it("Should calculate the price of insurance", (function () {
		expect(slcCtrl.calculate).toBeDefined();
		var insurance = "insurance";
		$httpBackend.expectPOST('https://localhost:8444/spring4/calculate', insurance).respond(200, "insurance");
		Insurance.calculate(insurance);
		$httpBackend.flush();
	}));

	it("Should calculate the price of insurance", (function () {
		expect(slcCtrl.createInsurance).toBeDefined();
		var insurance = "insurance";
		$httpBackend.expectPOST('https://localhost:8444/spring4/create', insurance).respond(200, "insurance");
		Insurance.create(insurance);
		$httpBackend.flush();
	}));

	afterEach(function() {
     	$httpBackend.verifyNoOutstandingExpectation();
     	$httpBackend.verifyNoOutstandingRequest();
	 });
});