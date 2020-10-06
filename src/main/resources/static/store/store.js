angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
            });
    };


    $scope.applyFilter = function () {
        $http({
            url: contextPath + '/api/v1/products',
            method: "GET",
            params: {
                    title: $scope.filter.title,
                    min_price: $scope.filter.min_price,
                    max_price: $scope.filter.max_price
                    }
        }).then(function (response) {
            $scope.Products = response.data;
        });
    }

    $scope.clearFilter = function () {
        $scope.filter = null;
        $scope.fillTable();
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});