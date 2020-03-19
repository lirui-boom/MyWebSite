app.service('commentService', function ($http) {

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../comment/search.do?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询评论
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../comment/findOneById.do?id=' + id);
    };

    /**
     * 更新评论
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../comment/update.do', entity);
    };

    /**
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../comment/add.do', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../comment/deleteIds.do?ids='+ids);
    };

    /**
     * 批量审核
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatusIds = function (ids, status) {
        return $http.get('../comment/updateStatusIds.do?ids=' + ids + '&status=' + status);
    };

    /**
     * 根据博客id查询评论信息
     * @param blogId
     * @returns {*}
     */
    this.getCommentByBlogId = function (blogId) {
        return $http.get('../comment/getCommentByBlogId.do?blogId=' + blogId);
    };

    /**
     * 根据动态id查询评论信息
     * @param trendsId
     * @returns {*}
     */
    this.getCommentByTrendsId = function (trendsId) {
        return $http.get('../comment/getCommentByTrendsId.do?trendsId=' + trendsId);
    };

    /**
     * 点赞
     * @param id
     * @returns {*}
     */
    this.updateCommentPraseCount = function (id) {
        return $http.get('../comment/updateCommentPraseCount.do?id=' + id);
    };
});