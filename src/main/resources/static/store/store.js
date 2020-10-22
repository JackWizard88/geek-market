angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    var PAGINATION_DIAPASON = 2;

    getCategories = function() {
        $http({
            url: contextPath + '/api/v1/products/categories',
            method: 'GET'
        })
        .then(function (response) {
            console.log('categories loaded');
            $scope.Categories = response.data;
        });
    }

    $scope.fillTable = function (pageIndex = 1) {
            $http({
                url: contextPath + '/api/v1/products',
                method: 'GET',
                params: {
                    title: $scope.filter ? $scope.filter.title : null,
                    min_price: $scope.filter ? $scope.filter.min_price : null,
                    max_price: $scope.filter ? $scope.filter.max_price : null,
                    category: $scope.filter ? [$scope.filter.category] : null,
                    p: pageIndex
                }
            })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesInd(pageIndex - PAGINATION_DIAPASON, pageIndex + PAGINATION_DIAPASON);
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            if (i > 0 && i <= $scope.ProductsPage.totalPages)
            arr.push(i);
        }
        return arr;
    }

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        })
        .then(function (response) {
            console.log('ok');
        });
    }

    $scope.clearFilter = function (productId) {
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
    getCategories();
});