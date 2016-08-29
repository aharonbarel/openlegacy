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

        .when('/client/', {
            templateUrl : 'pages/clientdetails.html',
            controller  : 'clientCtrl'
        })
});

app.controller('clientCtrl', function($scope, $http, $location) {
    $scope.clientList = [];
    $http.get("/openlegacy/web/client").then(function (response) {
        $scope.clientList = response.data;
    });

    $scope.getClient = function (clientId) {
        $location.path("#/client/" + clientId);
    }
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