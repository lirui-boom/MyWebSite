app.service('blogService', function ($http) {

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../blog/search.do?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询博客
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../blog/findOneById.do?id=' + id);
    };

    /**
     * 更新文章
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../blog/update.do', entity);
    };

    /**
     * 添加文章
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../blog/add.do', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../blog/deleteIds?ids='+ids);
    };

    /**
     * 批量发表
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatus = function (ids,status) {
        return $http.get('../blog/updateStatus.do?ids='+ids+'&status='+status);
    };
});