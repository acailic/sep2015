describe("InputController", function() {

    var InputController,Result, Payment, Transaction,$httpBackend;
  
     beforeEach(module("acquirer"));
    
   
    //pre svakog testa učitavamo mock PaymentService servis sa datom implementacijom
    /*  beforeEach(module(function($provide){

        $provide.factory('Payment', function(){
            return {
                send: function(payment) {return [{id:'1'}] } 
            }
        });

        $provide.factory('Transaction', function(){
            return {
                generate: function(transaction) {return [{id:'1'}] } 
            }
        });
      
    }));  */
 
 
 
    beforeEach(inject(function($controller, _Payment_ , _Transaction_,_Result_, _$httpBackend_) {
        Payment = _Payment_;
        Transaction=_Transaction_;
        Result=_Result_;
        $httpBackend=_$httpBackend_
        InputController = $controller("InputController", {
            Payment: Payment,
            Transaction: Transaction,
            Result:Result
        });
    }));  
 
    
     /*   it("should make request when use input changes and model should be valid", function () {
            $httpBackend.whenGET(appUrl + "/radnici/987?apiKey="+appKey).respond(404);
            $compile(inputElement)($scope);

            //kad promenimo vrednost jmbg polja znamo da se izvršava HTTP zahtev
            $scope.testForm.jmbg.$setViewValue("987");
            $httpBackend.flush();

            //kako je definisano da zahtev vraća 404 očekujemo da će forma biti validna
            expect($scope.testForm.$valid).toBe(true);
        });*/
    it("and so is a spec", function() {
        a = 1;

        expect(a+a).toBe(2);
    });
/* 
   it("should send payment service functions be called", function() {
        spyOn(Payment, "send");

        InputController.onIncomingParametar(2);

        
    });   
*/ /*
    it("should send payment service functions be called with param", function() {
        spyOn(Payment, "send");

        InputController.onIncomingParametar(2);

        expect(Payment.send).toHaveBeenCalledWith({id:'2'});
    });

     it("should call payment service function and receive custom return value", function() {
        spyOn(Payment, "send").and.returnValue(5);
        InputController.onIncomingParametar(2);
        
        //expect( InputController.onSuccess).toBe(5);
    });*/
   /*  
    it("should call employee service function and receive custom return value", function() {
        spyOn(employeeService, "addEmployee").and.returnValue(5);
        empCtrl.employee = {jmbg:'1', name:'boban'};

        expect(empCtrl.lastSaveSuccess).toBe(true);
        empCtrl.saveEmployee();

        expect(empCtrl.lastSaveSuccess).toBe(5);
    });*/

    //na kraju dodajemo ovaj blok da garantujemo da smo flushovali sve zahteve koje smo formirali,
    //kao i da ne postoje expect izrazi za HTTP zahteve koji nisu okinuti
    /* afterEach(function() {
        $httpBackend.verifyNoOutstandingRequest();
        $httpBackend.verifyNoOutstandingExpectation();
    }); */
});