var app = angular.module('openlegacy', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/main.html',
            controller  : 'clientCtrl'
        })

        .when('/new', {
            templateUrl : 'pages/clientform.html',
            controller  : 'postController'
        })

        .when('/client/:clientid',  {
            templateUrl : 'pages/clientdetails.html',
            controller  : 'clientCtrl'
        })
});

app.controller('clientCtrl', function($scope, $http, $route, $location) {
    $scope.clientList = [];
    $scope.initClientList = function () {
        $http.get('/openlegacy/web/client').then(function (response) {
            $scope.clientList = response.data;
        });
    };

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
    /*
    $scope.oneclient = {};
    $scope.getClient = function(clientid) {
        $http({
            method  :   'GET',
            url     :   '/openlegacy/web/client/' + clientid,
            headers :   {'Content-Type': 'application/json'}
        })
            .then(function(response) {
                $scope.oneclient = response.data;
                $location.path('/client/' + clientid);
            });
    };
    */
});

app.controller('postController', function($scope, $http, $location) {
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
});