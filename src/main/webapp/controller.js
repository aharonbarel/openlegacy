var app = angular.module('openlegacy', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/main.html',
            controller  : 'clientCtrl'
        })

        .when('/new', {
            templateUrl : 'pages/clientform.html',
            controller  : 'clientCtrl'
        })

        .when('/client/:clientid',  {
            templateUrl : 'pages/clientdetails.html',
            controller  : 'clientCtrl'
        })

        .when('/client/edit/:clientid',  {
            templateUrl : 'pages/editclient.html',
            controller  : 'clientCtrl'
        })
});

app.controller('clientCtrl', function($scope, $http, $route, $location) {
    //get single client
    $scope.oneclient = {};
    $scope.initClient = function () {
        var param = $route.current.params.clientid;
        $http.get('/openlegacy/web/client/' + param).then(function (response) {
            $scope.oneclient = response.data;
        });
    };
    $scope.getClient = function (clientid) {
        $location.path('/client/' + clientid);
        return clientid;
    };

    //get all clients
    $scope.clientList = [];
    $scope.initClientList = function () {
        $http({
            method  :   'GET',
            url     :   '/openlegacy/web/client',
            headers : {'Content-Type': 'application/json'}
        })
            .then(function (response) {
                $scope.clientList = response.data;
            });
    };

    //post new client
    $scope.client = {};
    $scope.submitForm = function() {
        $http({
            method  : 'POST',
            url     : '/openlegacy/web/client',
            data    : $scope.client,
            headers : {'Content-Type': 'application/json'}
        })
            .then(function() {
                $location.path('/');
            });
    };

    //update client
    $scope.editClient = function (client) {
        $location.path('/client/edit/' + client.id);
    };
    $scope.submitEdit = function() {
        $http({
            method  : 'PUT',
            url     : '/openlegacy/web/client/',
            data    : $scope.oneclient,
            headers : {'Content-Type': 'application/json'}
        })
            .then(function(response) {
                console.log(response);
                $location.path('/');
            });
    };

    //remove client
    $scope.removeClient = function (client) {
        $http({
            method  :   'DELETE',
            url     : '/openlegacy/web/client',
            data    : client,
            headers : {'Content-Type': 'application/json'}
        })
            .then(function () {
                $route.reload();
            });
    };
});
