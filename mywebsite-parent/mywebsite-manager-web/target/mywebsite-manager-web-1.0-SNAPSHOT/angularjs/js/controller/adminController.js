app.controller('adminController', function ($scope, $controller, adminService, uploadImgService) {

    $controller('baseController', {$scope: $scope});

    $scope.loginName = '';

    $scope.entity = {
        'admin': null,
        'name': null,
        'email': null,
        'phone': null,
        'picUrl':null
    };

    $scope.entity = {};
    /**
     * 获取管理员信息
     */
    $scope.getAdminInfo = function () {

        adminService.getLoginInfo().success(function (data) {

            if (data.status == 200) {

                $scope.loginName = data.data.admin;
                $scope.entity = data.data;
                $scope.entity.password = null;
            }
        });
    };

    /**
     * 修改管理员信息
     */
    $scope.updateAdminInfo = function () {

        adminService.update($scope.entity).success(function (data) {

            if (data.status == 200) {
                $scope.getAdminInfo();
                $scope.showSuccess();
                
            } else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };


    //发送修改密码的邮箱验证
    $scope.sendPasswordEmail = function () {

        if ($scope.entity.id == null) {
            return;
        }

        adminService.sendPasswordEmail($scope.entity.id).success(function (data) {

            if (data.status == 200) {

                $scope.showInfo("已为您发送了一封验证邮箱，请注意查收！");

            } else {
                $scope.showErrorInfo("发送验证邮箱出现错误！");
            }

        });
    };

    //校验表单
    $scope.check = function () {

        if ($scope.entity.password == '' || $scope.entity.password != $scope.entity.password2
            || $scope.entity.email == null || $scope.entity.email == ''
            || $scope.entity.token == null || $scope.entity.token == '') {
            return false
        }
        return true;
    };

    /**
     * 修改用户密码
     */
    $scope.updatePassword = function () {

        if (!$scope.check()) {
            $scope.showWarningInfo("表单信息有误,请检查表单填写！");
            return;
        }

        adminService.updatePassword($scope.entity).success(function (data) {

            if (data.status == 200) {
                $scope.showInfo("您的密码已完成更改！即将跳转至登录页面！");
                setTimeout(function () {
                    adminService.logout();
                    location.href = '/login.html';
                }, 3000);

            } else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    /**
     * 更新邮箱
     */
    $scope.updateEmail = function () {

        if (!$scope.check) {
            $scope.showWarningInfo("表单信息有误,请检查表单填写！");
            return;
        }

        adminService.updateEmail($scope.entity).success(function (data) {

            if (data.status == 200) {
                $scope.showInfo("您的邮箱已经重新绑定！");
            } else {
                $scope.showError();
            }

        });
    };


    /**
     * 发送邮箱绑定验证码
     */
    $scope.sendReEmail = function () {

        if (!$scope.check) {
            return;
        }

        adminService.sendReEmail($scope.entity).success(function (data) {

            if (data.status == 200) {
                $scope.showInfo("已为您发送了一封验证邮箱，请注意查收！");
            } else {
                $scope.showError();
            }
        });
    };

    /**
     * 根据邮箱查询用户
     */
    $scope.findOneByEmail = function () {

        if ($scope.entity.email == null || $scope.entity.email == '') {
            return;
        }

        adminService.findOneByEmail($scope.entity.email).success(function (data) {

            if (data.status == 200) {

                $scope.showSuccessInfo('该用户存在');
                $scope.entity = data.data;
                $scope.entity.password = null;

            } else {
                $scope.showWarningInfo(data.msg);
            }
        });
    };

    /**
     * 图片上传
     */
    $scope.uploadImg = function () {

        uploadImgService.uploadPic().success(function (data) {

            if (data.status == 200) {

                $scope.showSuccessInfo('图片上传成功');
                $scope.entity.picUrl = data.data;

            }else {

                $scope.showErrorInfo("图片上传出现错误");
            }

        });

    };
});