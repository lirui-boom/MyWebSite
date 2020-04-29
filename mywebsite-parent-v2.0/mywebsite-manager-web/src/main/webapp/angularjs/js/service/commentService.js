app.service('commentService', function ($http) {

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../comment/search?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询评论
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../comment/findOneById?id=' + id);
    };

    /**
     * 更新评论
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../comment/update', entity);
    };

    /**
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../comment/add', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../comment/deleteIds?ids='+ids);
    };

    /**
     * 批量审核
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatusIds = function (ids,status) {
        return $http.get('../comment/updateStatusIds?ids='+ids+'&status='+status);
    };

});