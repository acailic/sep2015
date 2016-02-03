describe("InputController", function() {

    var InputController, Payment, Transaction;
  
     beforeEach(module("acquirer.input"));
    
   
    //pre svakog testa učitavamo mock PaymentService servis sa datom implementacijom
    /* beforeEach(module(function($provide){

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
      
    })); */
 
 

    beforeEach(inject(function($controller, _Payment_ , _Transaction_) {
        Payment = _Payment_;
        Transaction=_Transaction_;
        InputController = $controller("InputController", {
            Payment: Payment,
            Transaction: Transaction
        });
    })); 
 
    
    it("and so is a spec", function() {
        a = 1;

        expect(a+a).toBe(2);
    });

   it("should send payment service functions be called", function() {
        spyOn(Payment, "send");

        InputController.onIncomingParametar(2);

        
    });   
 /*
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
    });*/
});