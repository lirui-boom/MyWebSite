app.controller('blogController', function ($scope, $controller,blogCategoryService, blogService,uploadImgService) {

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
        'title': null,
        'status': null,
        'cateId': null,
        'pageNum': 1,
        'pageSize': 5
    };

    //搜索结果
    $scope.resultMap = {
        'total': null,
        'totalPages': null,
        'pageNum': null,
        'pageSize': null
    };


    $scope.statusList = ['草稿', '已发表'];

    $scope.categoryList = [];
    /**
     * 条件搜索
     */
    $scope.search = function () {

        blogService.search($scope.searchEntity,$scope.searchEntity.pageNum,$scope.searchEntity.pageSize).success(function (data) {

            if (data.status == 200) {

                $scope.resultEntityList = data.data.rows;
                $scope.resultMap.total = data.data.total;
                $scope.resultMap.pageSize = $scope.searchEntity.pageSize;
                $scope.resultMap.totalPages = Math.ceil($scope.resultMap.total/$scope.resultMap.pageSize);
                $scope.resultMap.pageNum = $scope.searchEntity.pageNum;
                $scope.buildPageLabel();

            } else {
                $scope.showError(data.msg);
            }

        });
    };

    /**
     * 删除博客
     */
    $scope.deleteIds = function () {

        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        blogService.deleteIds($scope.selectIds).success(function (data) {

            if (data.status == 200) {

                $scope.reload();
                $scope.showSuccess();

            } else {
                $scope.showError(data.msg);
            }
        });
    };

    /**
     * 根据Id删除博客
     * @param id
     */
    $scope.deleteById = function (id) {

        if (id == null) {
            $scope.showWarningInfo('请选择要删除的id。');
            return;
        }

        blogService.deleteIds(id).success(function (data) {

            if (data.status == 200) {
                $scope.reload();
                $scope.showSuccess();
            }else {
                $scope.showErrorInfo(data.msg);
            }
        });
    };

    /**
     * 批量发表
     */
    $scope.updateStatusIds = function (status) {

        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            $scope.showWarningInfo('请选择要修改的博客id。');
            return;
        }

        blogService.updateStatusIds($scope.selectIds, status).success(function (data) {

            if (data.status == 200) {
                $scope.reload();
                $scope.showSuccess();
            } else {
                $scope.showErrorInfo(data.msg);
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

    //图片上传
    $scope.uploadImg = function () {

        uploadImgService.uploadPic().success(function (data) {

            if (data.status == 200) {

                $scope.showSuccessInfo('图片上传成功');
                $scope.tableEntity.picUrl = data.data;

            }else {

                $scope.showErrorInfo("图片上传出现错误");
            }
        });
    };

    //查询分类
    $scope.findCategory = function () {

        blogCategoryService.findAll().success(function (data) {

            if (data.status == 200) {
                $scope.optionList = data.data;
            }else {
                $scope.showErrorInfo("博客分类列表查询失败！");
            }
        });
    };

    $scope.save = function () {

        if ($scope.tableEntity == null) {
            $scope.showWarningInfo("您的操作出现异常！");
            return;
        }

        if ($scope.tableEntity.cateId == null) {
            $scope.showWarningInfo("请选择博客类型");
            return;
        }

        $scope.tableEntity.content = editor.html();

        var result;

        if ($scope.tableEntity.id == null) {
            result = blogService.add($scope.tableEntity);
        }else{
            result = blogService.update($scope.tableEntity);
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

    $scope.findOneById = function (id) {

        blogService.findOneById(id).success(function (data) {

            if (data.status == 200) {

                $scope.tableEntity = data.data;
                editor.html($scope.tableEntity.content);
            }else {
                $scope.showErrorInfo("出现错误，查询失败！");
            }
        });
    };

    $scope.getCategoryName = function (id) {

        for (var i = 0; i < $scope.optionList.length; i++) {

            if ($scope.optionList[i]['id'] == id) {
                return $scope.optionList[i]['name'];
            }
        }
        return null;
    };

    $scope.clearTable = function () {

        $scope.tableEntity = null;
        editor.html('');
    };
});