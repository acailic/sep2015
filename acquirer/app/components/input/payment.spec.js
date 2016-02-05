describe("InputController", function() {

    var InputController,Result, Payment, Transaction,$httpBackend;
     //Transaction, Payment,  $stateParams, $timeout,  Result,  $window
     beforeEach(module("acquirer"));
    
 
 
 
    beforeEach(inject(function($controller, _Transaction_ , _Payment_,_$stateParams_,_$timeout_ , _Result_, _$window_ ,_$httpBackend_) {
       
        Transaction=_Transaction_;
        Payment = _Payment_;
        $stateParams=_$stateParams_;
        $timeout= _$timeout_ ;
        Result=_Result_;
        $window=_$window_; 
        $httpBackend=_$httpBackend_;

        $httpBackend.whenGET("app/components/acquirer/header.html").respond("<div/>");
        $httpBackend.whenGET("app/components/acquirer/menu.html").respond("<div/>");
        $httpBackend.whenGET("app/components/acquirer/footer.html").respond("<div/>");
        $httpBackend.whenGET("app/components/input/input.html").respond("<div/>");

        $httpBackend.whenGET('http://localhost:8081/Acquirer_back/payment/init/payment?id=1').respond(200, ['1','2','3']);
        InputController = $controller("InputController", {
            Transaction: Transaction,
            Payment: Payment,
            $stateParams:$stateParams,
            $timeout: $timeout,
            Result:Result,
            $window:$window,
            $httpBackend: $httpBackend
        });
    }));  

    it("Variables to be defined", function() {
        expect(InputController.transaction ).toBeDefined();
        expect(InputController.resultTrans ).toBeDefined();
    });
    
     it("Methods to be defined", function() {
       
        expect(InputController.onIncomingParametar ).toBeDefined();
        expect(InputController.generatingTransaction ).toBeDefined();
        expect(InputController.showModalProgress ).toBeDefined();
        expect(InputController.showConfirm ).toBeDefined();
        expect(InputController.showError).toBeDefined();
    }); 

     it("Should get transaction data, paymentId, amount, CSRFtoken",function()  {
        // $httpBackend.flush();
        $httpBackend.expectGET("http://localhost:8081/Acquirer_back/payment/init/payment?id=1").respond(200, ['1','2','3']);
        Payment.send('1');
        $httpBackend.flush();
        expect(InputController.transaction.paymentId).toBeDefined();
        expect(InputController.transaction.amount).toBeDefined();
        expect(InputController.transaction.CSRFToken).toBeDefined();
    });

     it("Should get generate data", function()  {
        
        var transaction='transaction';
        $httpBackend.expectPOST('http://localhost:8081/Acquirer_back/payment/confirm', transaction).respond(200, [1]);
        Transaction.generate(transaction);
        $httpBackend.flush();
        expect(InputController.resultTrans).toBeDefined();

    });


    it("Should get result data", function()  {
         
        var result= "SUCCESSFUL";
        $httpBackend.expectPOST('https://localhost:8080/spring4/transactionResults', result).respond(200, ['redirectlink']);
        Result.sending("SUCCESSFUL");
        $httpBackend.flush();
        expect(InputController.redirectUrl).toBeDefined();
        
    }); 
    
    //na kraju dodajemo ovaj blok da garantujemo da smo flushovali sve zahteve koje smo formirali,
    //kao i da ne postoje expect izrazi za HTTP zahteve koji nisu okinuti
     afterEach(function() {
        $httpBackend.verifyNoOutstandingRequest();
        $httpBackend.verifyNoOutstandingExpectation();
    });  
});