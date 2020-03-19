app.controller('personalController', function ($scope, $controller, personalService,uploadImgService) {

    $controller('baseController', {$scope: $scope});

    $scope.entity = {};

    $scope.find = function () {

        personalService.findOne().success(function (data) {

            if (data.status == 200) {

                $scope.entity = data.data;
                editor.html($scope.entity.perDesc);

            }else {

                $scope.showErrorInfo('信息查询失败');
            }

        });

    };

    $scope.update = function () {

        if ($scope.entity.id == null) {
            $scope.showWarningInfo('无法更新个人信息');
            return;
        }

        $scope.entity.perDesc = editor.html();

        personalService.update($scope.entity).success(function (data) {

            if (data.status == 200) {
                alert("信息修改成功！");
                $scope.showSuccess();
            }else {
                $scope.showErrorInfo('更新个人信息失败');
            }
        });
    };

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