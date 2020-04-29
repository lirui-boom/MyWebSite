app.controller("registerController", function ($scope,$controller,registerService) {

    $controller('baseController', {$scope: $scope});

    $scope.entity = {'admin':null,'email':null,'password':null,'password2':null, 'agree': null};

    $scope.check = function () {

        $scope.errorMsg = '';

        if ($scope.entity.admin == null || $scope.entity.email == null || $scope.entity.password == null
            || $scope.entity.password2 == null) {
            $scope.errorMsg = '请填写相关信息！';
            return false;

        }else if ($scope.entity.agree != true) {
            $scope.errorMsg = '请勾选同意相关条款！';
            return false;
        }

        if ($scope.entity.password != $scope.entity.password2) {
            $scope.errorMsg = '两次输入的密码不一致！';
            return false;
        }

        return true;
    };

    $scope.register = function () {

        if ($scope.check()) {

            registerService.register($scope.entity).success(function (data) {

                if (data.status == 200) {
                    $scope.errorMsg = null;
                    alert("注册成功，请注意邮箱信息并激活邮箱！");
                    location.href = '/registinfo.html';
                }else {
                    $scope.errorMsg = data.msg;
                }
            });
        }
    };

});