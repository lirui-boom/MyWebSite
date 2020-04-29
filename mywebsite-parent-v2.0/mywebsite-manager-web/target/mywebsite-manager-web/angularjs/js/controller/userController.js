app.controller('userController', function ($scope, $controller, userService) {

    $controller('baseController', {$scope: $scope});

    $scope.reload = function () {
        $scope.selectIds = [];
        $scope.search();
    };

    $scope.searchEntity = {
        'pageSize':5,
        'pageNum':1
    };

    //搜索结果
    $scope.resultMap = {
        'total': null,
        'totalPages': null,
        'rows': null,
        'pageNum': null,
        'pageSize': null
    };


    $scope.search = function () {

        userService.search($scope.searchEntity,$scope.searchEntity.pageNum,$scope.searchEntity.pageSize).success(function (data) {

            if (data.status == 200) {
                $scope.resultEntityList = data.data.rows;
                $scope.resultMap.total = data.data.total;
                $scope.resultMap.pageSize = $scope.searchEntity.pageSize;
                $scope.resultMap.totalPages =$scope.resultMap.totalPages = Math.ceil($scope.resultMap.total/$scope.resultMap.pageSize);
                $scope.resultMap.pageNum = $scope.searchEntity.pageNum;
                $scope.buildPageLabel();

            } else {
                $scope.showError(data.msg);
            }
        });
    };

    //构建分页栏
    $scope.buildPageLabel = function () {
        //分页栏
        $scope.pageLabel = [];

        var start = 1;
        var end = $scope.resultMap.totalPages;

        if ($scope.resultMap.totalPages > 5) {

            if ($scope.resultMap.pageNum <= 3) {
                end = 5;
            } else if ($scope.resultMap.pageNum > $scope.resultMap.totalPages - 2) {
                start = $scope.resultMap.totalPages - 4;
            }else {
                start = $scope.resultMap.pageNum -2;
                end = $scope.resultMap.pageNum + 2;
            }

        }

        for (var i = start; i <= end; i++) {
            $scope.pageLabel.push(i);
        }
    };

    /**
     * 查询某页
     * @param pageNum
     */
    $scope.queryByPage = function (pageNum) {

        if (pageNum > $scope.searchEntity.totalPages) {
            return;
        }
        $scope.searchEntity.pageNum = pageNum;
        $scope.reload();
    };

    //是否为第一页
    $scope.isFirstPage = function () {
        return ($scope.resultMap.pageNum == 1);
    };

    //是否为最后一页
    $scope.isEndPage = function () {
        return ($scope.resultMap.pageNum == $scope.resultMap.totalPages);
    };


    $scope.findOneById = function (id) {

        userService.findOneById(id).success(function (data) {

            if (data.status == 200) {
                $scope.tableEntity = data.data;
            } else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    $scope.save = function () {

        if ($scope.tableEntity == null || $scope.tableEntity.name == null ||
        $scope.tableEntity.nickName == null || $scope.tableEntity.email == null) {
            $scope.showWarningInfo("您的参数不合法！");
            return;
        }

        var result;

        if ($scope.tableEntity.id == null) {
            result = userService.add($scope.tableEntity);
        }else{
            result = userService.update($scope.tableEntity);
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

    /**
     * 删除联系人
     */
    $scope.deleteIds = function () {

        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        userService.deleteIds($scope.selectIds).success(function (data) {

            if (data.status == 200) {

                $scope.reload();
                $scope.showSuccess();

            } else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    /**
     * 根据Id删除联系人
     * @param id
     */
    $scope.deleteById = function (id) {

        if (id == null) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        userService.deleteIds(id).success(function (data) {

            if (data.status == 200) {
                $scope.reload();
                $scope.showSuccess();
            }else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };
});