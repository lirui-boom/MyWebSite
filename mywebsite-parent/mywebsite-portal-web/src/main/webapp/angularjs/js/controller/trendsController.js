app.controller('trendsController', function ($scope, $controller,trendsService,commentService,userService) {

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

        trendsService.search($scope.searchEntity,$scope.searchEntity.pageNum,$scope.searchEntity.pageSize).success(function (data) {

            if (data.status == 200) {

                $scope.resultEntityList = data.data.rows;
                $scope.resultMap.total = data.data.total;
                $scope.resultMap.pageSize = $scope.searchEntity.pageSize;
                $scope.resultMap.totalPages = Math.ceil($scope.resultMap.total/$scope.resultMap.pageSize);
                $scope.resultMap.pageNum = $scope.searchEntity.pageNum;
                $scope.buildPageLabel();

            } else {
               alert(data.msg);
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

        trendsService.findOneById(id).success(function (data) {

            if (data.status == 200) {

                $scope.tableEntity = data.data;
            }else {
                alert("出现错误，查询失败！");
            }
        });
    };

    $scope.showIndexTrendsList = [];

    $scope.showIndexTrends = function () {

        trendsService.search({'status':'1'}, 1, 2).success(function (data) {

            if (data.status == 200) {
                $scope.showIndexTrendsList = data.data.rows;
            }else {
                alert("动态查询失败！");
            }
        });
    };

    $scope.showContent = function (id,content) {
        var div = document.getElementById(id);

        if (div == null) {
            return;
        }

        div.innerHTML = content;
    };

    $scope.getDetails = function () {

        var trendsId = $scope.GetQueryString("id");

        if (trendsId == null) {
            alert("参数不合法！");
            return;
        }

        trendsService.findOneById(trendsId).success(function (data) {

            if (data.status == 200) {
                $scope.trends = data.data;
                $scope.getCommentByTrendsId();
            }else {
                alert(data.msg);
            }
        });
    };

    $scope.updatePraseCount = function () {

        if ($scope.trends == null) {
            return;
        }

        $scope.trends.praseCount = $scope.trends.praseCount + 1;

        trendsService.update($scope.trends).success(function (data) {

            if (data.status != 200) {
                alert(data.msg);
            }
        });
    };


    /*--------------评论相关-------------*/



    $scope.commentEntity = {};
    $scope.userEntity = {};

    $scope.findOneByEmail = function (email) {

        RemoveDisable();

        if (email == null || email == '') {
            $scope.userEntity = {};
            return;
        }

        userService.findOneByEmail(email).success(function (data) {

            if (data.status == 200) {
                $scope.userEntity = data.data;
                ChangeDisable(true);
            } else {
                if ($scope.userEntity.email != null && $scope.userEntity.email != '') {
                    var email = $scope.userEntity.email;
                    $scope.userEntity = {};
                    $scope.userEntity.email = email;
                }
            }

        });
    };

    $scope.addUser = function () {
        if ($scope.userEntity.id == null) {
            userService.add($scope.userEntity).success(function (data) {

                if (data.status == 200) {

                    $scope.userEntity = data.data;
                    $scope.addComment();

                } else {
                    alert(data.msg);
                }
            });
        } else {
            $scope.addComment();
        }
    };

    $scope.addComment = function () {

        if ($scope.userEntity.id == null || $scope.commentEntity.content == null || $scope.trends.id == null) {
            alert("参数不合法");
            return;
        }

        $scope.commentEntity.uid = $scope.userEntity.id;
        $scope.commentEntity.trendsId = $scope.trends.id;

        commentService.add($scope.commentEntity).success(function (data) {

            if (data.status == 200) {
                alert("评论成功，审核成功后将展示。");
            } else {
                alert(data.msg);
            }
        });
    };


    $scope.getCommentByTrendsId = function () {

        if ($scope.trends == null || $scope.trends.id == null) {
            alert("参数不合法");
            return;
        }

        commentService.getCommentByTrendsId($scope.trends.id).success(function (data) {

            if (data.status == 200) {
                $scope.commentResultList = data.data;
                $scope.addUserInfo();
            } else {
                alert(data.msg);
            }

        });
    };

    $scope.addUserInfo = function () {

        userService.findAll().success(function (data) {

            if (data.status == 200) {

                $scope.userList = data.data;

                for (var i = 0; i < $scope.commentResultList.length; i++) {

                    //评论实体增添user属性
                    for (var j = 0; j < $scope.userList.length; j++) {
                        if ($scope.commentResultList[i]['comment'].uid == $scope.userList[j].id) {
                            $scope.commentResultList[i]['comment'].user = $scope.userList[j];
                        }
                    }
                    //评论回复列表增添user属性
                    for (var j = 0; j < $scope.commentResultList[i]['childReplyList'].length; j++) {

                        for (var k = 0; k < $scope.userList.length; k++) {
                            if ($scope.commentResultList[i]['childReplyList'][j].uid == $scope.userList[k].id) {
                                $scope.commentResultList[i]['childReplyList'][j].user = $scope.userList[k];
                            }
                        }
                    }
                }

                //增加回复用户id

                for (var i = 0; i < $scope.commentResultList.length; i++) {

                    for (var j = 0; j < $scope.commentResultList[i]['childReplyList'].length; j++) {

                        for (var k = 0; k < $scope.commentResultList.length; k++) {

                            if ($scope.commentResultList[i]['childReplyList'][j].toCid == $scope.commentResultList[k]['comment'].id) {

                                var user = $scope.getUser($scope.commentResultList[k]['comment'].uid);
                                $scope.commentResultList[i]['childReplyList'][j].toUser = user;
                            }

                        }
                    }
                }

                for (var i = 0; i < $scope.commentResultList.length; i++) {

                    for (var j = 0; j < $scope.commentResultList[i]['childReplyList'].length; j++) {

                        for (var k = 0; k < $scope.commentResultList[i]['childReplyList'].length; k++) {

                            if ($scope.commentResultList[i]['childReplyList'][j].toCid == $scope.commentResultList[i]['childReplyList'][k].id) {

                                var user = $scope.getUser($scope.commentResultList[i]['childReplyList'][k].uid);
                                $scope.commentResultList[i]['childReplyList'][j].toUser = user;
                            }

                        }
                    }
                }
            }

        });
    };

    $scope.getUser = function (id) {

        if ($scope.userList == null || $scope.userList.length == 0 || id == null) {
            return null;
        }

        for (var i = 0; i < $scope.userList.length; i++) {

            if (id == $scope.userList[i]['id']) {
                return $scope.userList[i];
            }
        }
        return null;
    };

    $scope.replyTo = function (id,nickName) {

        if (id == null) {
            $scope.commentEntity.toCid = null;
            $scope.replyInfo = null;
            return;
        }

        $scope.commentEntity.toCid = id;
        $scope.replyInfo = '@' + nickName;
    };

    $scope.updateCommentPraseCount = function (id) {

        commentService.updateCommentPraseCount(id).success(function (data) {

            if (data.status == 200) {
                $scope.getDetails();
            }else {
                alert(data.msg);
            }
        });
    }
});