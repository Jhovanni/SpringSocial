var springSocial = angular.module("springSocial", []);
springSocial.controller("LoginCtrl", function ($scope, $http) {
    $http.get('/api/session', []).then(function (data) {
        $scope.usuario = data.data;
    }, function (err) {
        console.log("Error", err);
    });
    $scope.salir = function () {
        $http.delete("api/session", []).then(function () {
            $scope.usuario = null;
        }, function (err) {
            console.log("Sin poder salir", err);
        });
    };
}); 