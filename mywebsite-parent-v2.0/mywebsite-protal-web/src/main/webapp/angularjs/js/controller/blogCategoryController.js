app.controller('blogCategoryController', function ($scope, $controller,blogCategoryService) {

    $controller('baseController', {$scope: $scope});

    $scope.findAll = function () {

            blogCategoryService.findAll().success(function (data) {

                if (data.status == 200) {

                    $scope.resultEntityList = data.data;
                }else {
                    $scope.showErrorInfo(data.msg);
                }

            });
    };

    $scope.reload = function () {
        $scope.findAll();
    };

    $scope.findOneById = function (id) {

        blogCategoryService.findOneById(id).success(function (data) {


            if (data.status == 200) {

                $scope.tableEntity = data.data;
            }else {

                alert(data.msg);
            }

        });
    };


    $scope.save = function () {

        if ($scope.tableEntity == null) {
            $scope.showWarningInfo("您的操作出现异常！");
            return;
        }

        var result;

        if ($scope.tableEntity.id == null) {

            result = blogCategoryService.add($scope.tableEntity);

        }else{

            result = blogCategoryService.update($scope.tableEntity);
        }

        result.success(function (data) {

            if (data.status == 200) {

                $scope.showSuccess();
                $scope.reload();
            }else {

                $scope.showErrorInfo("出现错误，操作失败！");
            }
        });
    };

    $scope.deleteIds = function () {

        blogCategoryService.deleteIds($scope.selectIds).success(function (data) {

            if (data.status == 200) {
                $scope.showSuccess();
                $scope.reload();
            }else {
                $scope.showErrorInfo("出现错误，操作失败！");
            }

        });
    };

    $scope.deleteById = function (id) {

        blogCategoryService.deleteIds(id).success(function (data) {

            if (data.status == 200) {
                $scope.showSuccess();
                $scope.reload();
            }else {
                $scope.showErrorInfo("出现错误，操作失败！");
            }

        });
    };
});