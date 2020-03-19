app.controller('accountController', function ($scope, $controller, adminService) {

    $controller('baseController', {$scope: $scope});

    /**
     * 重新加载页面
     */
    $scope.reload = function () {
        $scope.selectIds = [];
        $scope.search();
    };

    //搜索条件
    $scope.searchEntity = {
        'name': null,
        'status': null,
        'pageNum': 1,
        'pageSize': 5
    };

    //搜索结果
    $scope.resultMap = {
        'total': null,
        'totalPages': null,
        'rows': null,
        'pageNum': null,
        'pageSize': null
    };


    $scope.statusList = ['未激活', '待审核', '已审核', '已驳回'];
    /**
     * 条件搜索
     */
    $scope.search = function () {

        adminService.search($scope.searchEntity,$scope.searchEntity.pageSize,$scope.searchEntity.pageNum).success(function (data) {

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

    /**
     * 删除用户
     */
    $scope.deleteIds = function () {

        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        adminService.deleteIds($scope.selectIds).success(function (data) {

            if (data.status == 200) {

                $scope.reload();
                $scope.showSuccess();

            } else {
                $scope.showError(data.msg);
            }
        });
    };

    /**
     * 根据Id删除用户
     * @param id
     */
    $scope.deleteById = function (id) {

        if (id == null) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        adminService.deleteIds(id).success(function (data) {

            if (data.status == 200) {
                $scope.reload();
                $scope.showSuccess();
            }else {
                $scope.showError(data.msg);
            }
        });
    };

    /**
     * 批量审核
     */
    $scope.updateStatusIds = function (status) {

        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            $scope.showWarningInfo('请选择要审核的id。');
            return;
        }

        adminService.updateStatusIds($scope.selectIds, status).success(function (data) {

            if (data.status == 200) {
                $scope.reload();
                $scope.showSuccess();
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
});