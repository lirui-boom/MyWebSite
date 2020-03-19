app.service('trendsService', function ($http) {

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../trends/search.do?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询动态
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../trends/findOneById.do?id=' + id);
    };

    /**
     * 更新动态
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../trends/update.do', entity);
    };

    /**
     * 添加动态
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../trends/add.do', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../trends/deleteIds.do?ids='+ids);
    };

    /**
     * 批量发表
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatusIds = function (ids,status) {
        return $http.get('../trends/updateStatusIds.do?ids='+ids+'&status='+status);
    };
});