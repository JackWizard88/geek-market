angular.module('app').controller('registrationController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.register = function () {
                $http({
                    url: contextPath + '/api/v1/user/register',
                    method: 'POST',
                    params: {
                        login: $scope.newUser ? $scope.newUser.login : null,
                        pass: $scope.newUser ? $scope.newUser.pass : null,
                        email: $scope.newUser ? $scope.newUser.email : null
                    }
                })
                .then(function (response) {
                    document.location.href = '#!/auth';
                });
        };
});