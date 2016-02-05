(function() {
    angular
        .module('merchante2e', ['merchant', 'ngMockE2E'])
        .run(merchante2ePrep);
    function merchante2ePrep($httpBackend, $location) {
        
        $httpBackend.whenGET(new RegExp('assets\/.*')).passThrough();
        $httpBackend.whenGET("app/components/core/header.html").passThrough();
        $httpBackend.whenGET("app/components/core/menu.html").passThrough();
        $httpBackend.whenGET("app/components/insurance/insurance.html").passThrough();
        $httpBackend.whenGET("app/components/sale/sale.html").passThrough();
        $httpBackend.whenGET("app/components/sale/sale-wizard-part1.html").passThrough();
        $httpBackend.whenGET("app/components/sale/sale-wizard-part2.html").passThrough();
        $httpBackend.whenGET("app/components/sale/sale-wizard-part3.html").passThrough();
        $httpBackend.whenGET("app/components/modal/modalPayment.html").passThrough();
        $httpBackend.whenGET("app/components/calculator/calculator.html").passThrough();


        
        var accomodation = [
            {id:"1", name: "Acc1"},
            {id:"2", name: "Acc2"},
            {id:"3", name: "Acc3"}
        ];
        var ages = [
            {id:"1", name: "Do18"},
            {id:"2", name: "18-60"},
            {id:"3", name: "Od60"}
        ];
        var alternative = [
            {id:"1", name: "Alt1"},
            {id:"2", name: "Alt2"},
            {id:"3", name: "Alt3"}
        ];
        var brands = [
            {id:"1", name: "Brand1"},
            {id:"2", name: "Brand2"},
            {id:"3", name: "Brand3"}
        ];
        var casualties = [
            {id:"1", name: "Poplava"},
            {id:"2", name: "Pozar"},
            {id:"3", name: "Zemljotres"}
        ];
        var cities = [
            {id:"1", name: "Beograd"},
            {id:"2", name: "Subotica"},
            {id:"3", name: "Zrenjanin"}
        ];
        var max_values = [
            {id:"1", name: "10.000EUR"},
            {id:"2", name: "20.000EUR"},
            {id:"3", name: "30.000EUR"}
        ];
        var regions = [
            {id:"1", name: "Evropa"},
            {id:"2", name: "Azija"},
            {id:"3", name: "Amerika"}
        ];
        var repair = [
            {id:"1", name: "Rep1"},
            {id:"2", name: "Rep2"},
            {id:"3", name: "Rep3"}
        ];
        var sports = [
            {id:"1", name: "fudbal"},
            {id:"2", name: "Sport2"},
            {id:"3", name: "Sport3"}
        ];
        var towing = [
            {id:"1", name: "Towing1"},
            {id:"2", name: "Towing2"},
            {id:"3", name: "Towing3"}
        ];




        //dobavljanje inicijalnih podataka (data_init) za comboboxeve
        $httpBackend.whenGET('https://localhost:8444/spring4/data_init').respond(200, {accomodation: accomodation, ages: ages, alternative: alternative, brands: brands, casualties: casualties, cities: cities, max_values: max_values, regions: regions, repair: repair, sports: sports, towing: towing});

        //dobavljanje cene osiguranja (putnog, stambenog, pomoc na putu)
        $httpBackend.whenPOST('https://localhost:8444/spring4/calculate').respond(200, {travel_price: 20, home_price: 0, vehicle_price: 0});

        //dobavljanje cene osiguranja (putnog)
        $httpBackend.whenPOST('https://localhost:8444/spring4/calculateTravel').respond(200, {travel_price: 20, home_price: 0, vehicle_price: 0});

        //dobavljanje cene osiguranja (stambenog)
        $httpBackend.whenPOST('https://localhost:8444/spring4/calculateHome').respond(200, {travel_price: 0, home_price: 10, vehicle_price: 0});

        //dobavljanje cene osiguranja (pomoc na putu)
        $httpBackend.whenPOST('https://localhost:8444/spring4/calculateVehicle').respond(200, {travel_price: 0, home_price: 0, vehicle_price: 5});

        //dobvaljanja url-a za redirekciju za placanje prilikom potvrde kupovine
        //vraca se url neki koji postoji u lokalu cisto da se prodje wizard za prodaju do kraja i proveri da li radi redirekcija (u realnoj situaciji redirektovace se na Acquirer-a)
        $httpBackend.whenPOST('https://localhost:8444/spring4/create').respond(200, {url: 'https://localhost:8001/#/insurance'});

    }
})();