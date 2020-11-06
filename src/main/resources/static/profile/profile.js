angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.profileRequest = function () {
        $http({
            url: contextPath + '/api/v1/user/profile',
            method: 'GET'
        })
            .then(function (response) {
                $scope.profile = response.data;
                console.log(response.data);
                $scope.checkGender();
            });
    };

    $scope.checkGender = function () {
        if ($scope.profile.sex) {
            document.getElementById('r1').checked=true;
        } else document.getElementById('r2').checked=true;
    }

    $scope.saveDetails = function () {
        $http({
            url: contextPath + '/api/v1/user/save',
            method: 'POST',
            params: {
               firstName: $scope.profile ? $scope.profile.firstName : null,
               lastName: $scope.profile ? $scope.profile.lastName : null,
               phoneNumber: $scope.profile ? $scope.profile.phoneNumber : null,
               birthDate: $scope.profile ? $scope.profile.birthDate : null,
               city: $scope.profile ? $scope.profile.city : null,
               sex: $scope.profile ? $scope.profile.sex : null,
               password: $scope.profile ? $scope.profile.pass : null
            }
        })
        .then(function (response) {
            if (response.data === 'FORBIDDEN') {
                window.alert("Неверный пароль");
                return;
            }
            $scope.profileRequest();
            window.alert("Данные успешно обновленны");
        });
    }

    $scope.profileRequest();
});