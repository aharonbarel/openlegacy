var app = angular.module('openlegacy', ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider

        .when('/', {
            templateUrl: 'pages/main.html',
            controller: 'clientCtrl'
        })

        .when('/new', {
            templateUrl: 'pages/clientform.html',
            controller: 'postController'
        })
});

app.controller('clientCtrl', function($scope, $http) {
    $scope.myData = [];
    $http.get("/openlegacy/web/client").then(function (response) {
        $scope.myData = response.data;
    });
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