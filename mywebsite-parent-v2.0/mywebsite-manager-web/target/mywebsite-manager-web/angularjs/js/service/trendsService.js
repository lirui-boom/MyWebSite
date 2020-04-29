app.service('trendsService', function ($http) {

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../trends/search?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询动态
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../trends/findOneById?id=' + id);
    };

    /**
     * 更新动态
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../trends/update', entity);
    };

    /**
     * 添加动态
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../trends/add', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../trends/deleteIds?ids='+ids);
    };

    /**
     * 批量发表
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatusIds = function (ids,status) {
        return $http.get('../trends/updateStatusIds?ids='+ids+'&status='+status);
    };
});