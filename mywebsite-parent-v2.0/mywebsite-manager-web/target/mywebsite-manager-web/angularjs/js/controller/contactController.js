app.controller('contactController', function ($scope,$controller,contactService,userService) {

    $controller('baseController', {$scope: $scope});

    $scope.reload = function () {
        $scope.selectIds = [];
        $scope.search();
    };

    $scope.searchEntity = {
        'status': null,
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

    $scope.statusList = ['未回复', '已回复'];

    $scope.findAllUser = function () {
        userService.findAll().success(function (data) {

            if (data.status == 200) {

                $scope.userList = data.data;
            }else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    $scope.search = function () {

        contactService.search($scope.searchEntity,$scope.searchEntity.pageNum,$scope.searchEntity.pageSize).success(function (data) {

            if (data.status == 200) {
                $scope.resultEntityList = data.data.rows;
                $scope.resultMap.total = data.data.total;
                $scope.resultMap.pageSize = $scope.searchEntity.pageSize;
                $scope.resultMap.totalPages =$scope.resultMap.totalPages = Math.ceil($scope.resultMap.total/$scope.resultMap.pageSize);
                $scope.resultMap.pageNum = $scope.searchEntity.pageNum;

                for (var i = 0; i < $scope.resultEntityList.length; i++) {

                    for (var j = 0; j < $scope.userList.length; j++) {

                        if ($scope.resultEntityList[i].uid == $scope.userList[j]['id']) {
                            $scope.resultEntityList[i]['user'] = $scope.userList[j];
                        }
                    }
                }

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

        contactService.findOneById(id).success(function (data) {

            if (data.status == 200) {
                $scope.tableEntity = data.data;
            } else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };


    $scope.reply = function () {

        if ($scope.tableEntity == null || $scope.tableEntity.id == null
            || $scope.tableEntity.replyMsg == null || $scope.tableEntity.uid == null
            || $scope.tableEntity.status == null) {
            $scope.showWarningInfo("参数不合法");
            return;
        }

        contactService.reply($scope.tableEntity).success(function (data) {

            if (data.status == 200) {
                $scope.showSuccessInfo("信息回复成功！");
                $scope.reload();
            }else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    $scope.deleteIds = function () {

        if ($scope.selectIds.length == 0) {
            $scope.showWarningInfo("请选择删除id.");
            return;
        }

        contactService.deleteIds($scope.selectIds).success(function (data) {

            if (data.status == 200) {
                $scope.showSuccess();
                $scope.reload();
            } else {
                $scope.showErrorInfo(data.msg);
            }

        });
    };

    $scope.deleteById = function (id) {

        contactService.deleteIds(id).success(function (data) {

            if (data.status == 200) {
                $scope.showSuccess();
                $scope.reload();
            }else {
                $scope.showErrorInfo(data.msg);
            }
        });
    }
});