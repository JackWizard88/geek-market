angular.module('app').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    getOrders = function() {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        })
        .then(function (response) {
            console.log('orders loaded');
            $scope.OrdersList = response.data;
        });
    }

    getOrders();
});