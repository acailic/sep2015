(function() {
    angular
        .module('appe2e', ['acquirer', 'ngMockE2E'])
        .run(appe2ePrep);

    function appe2ePrep($httpBackend, $location) {
        
        $httpBackend.whenGET("app/components/acquirer/header.html").passThrough(); 
        $httpBackend.whenGET("app/components/acquirer/footer.html").passThrough(); 
        $httpBackend.whenGET("app/components/acquirer/menu.html").passThrough();
        $httpBackend.whenGET("app/components/input/input.html").passThrough();
        $httpBackend.whenGET("app/components/modal/progress-modal.html").passThrough();
        $httpBackend.whenGET("app/components/modal/success-modal.html").passThrough();
        $httpBackend.whenGET("app/components/modal/error-modal.html").passThrough();
        

         //init paymenta
        $httpBackend.whenGET("http://localhost:8081/Acquirer_back/payment/init/payment?id=1").respond(200,
            {paymentId:"1", amount: "1234", csrftoken: "Iv1ov"});
        
        //za gresku
          $httpBackend.whenGET("http://localhost:8081/Acquirer_back/payment/init/payment?=").respond(200,
            {paymentId:"", amount: "", csrftoken: ""});
 
        //generisana transakcija
        $httpBackend.whenPOST("http://localhost:8081/Acquirer_back/payment/confirm").respond(200,
            {merchantOrderId: '1', acquirerOrderId: '1', acquirerTimeStamp:new Date(),paymentId:'1', state: 'SUCCESSFULL'}
        );

         //generisana transakcija
        $httpBackend.whenPOST("https://localhost:8080/spring4/transactionResults").respond(200,
             {url:' https://localhost:8001/success.html' });

    }
})();