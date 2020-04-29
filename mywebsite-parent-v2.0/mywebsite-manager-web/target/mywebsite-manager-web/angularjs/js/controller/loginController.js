app.controller('loginController', function ($scope,$controller,loginService) {

    $controller('baseController', {$scope: $scope});


    $scope.entity = {};

    $scope.login = function () {

        if ($scope.entity.username == null || $scope.entity.password == null) {
            return;
        }

        loginService.login($scope.entity).success(function (data) {

            $scope.msg = "";

            if (data.status != null && data.status != undefined && data.status != 200) {
                $scope.msg = data.msg;
            }

        });
    };

});