app.controller('loginController', function ($scope,$controller,loginService) {

    $controller('baseController', {$scope: $scope});


    $scope.entity = {};

    $scope.login = function () {

        if ($scope.entity.admin == null || $scope.entity.password == null) {
            return;
        }

        loginService.login($scope.entity).success(function (data) {

            if (data.status != 200) {
                $scope.msg = data.msg;
            }

        });
    };

});