angular.module('app').controller('orderConfirmationController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.cart = response.data;
            });
    };

    $scope.placeOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders/place',
            method: 'POST',
            params: {
                name: $scope.order ? $scope.order.name : null,
                address: $scope.order ? $scope.order.address : null,
                phone_number: $scope.order ? $scope.order.phone_number : null
                }
            })
            .then(function (response) {
                console.log("order placed");
                $scope.order = null;
                document.location.href = '#!/orders';
            });
    }

    $scope.cartContentRequest();
});