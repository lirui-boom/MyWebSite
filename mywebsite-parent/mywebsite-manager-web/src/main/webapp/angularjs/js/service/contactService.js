app.service('contactService', function ($http) {

    /**
     * 条件分页搜索查询
     * @param searchEntity
     * @param pageNum
     * @param pageSize
     * @returns {*}
     */
    this.search = function (searchEntity, pageNum, pageSize) {
        return $http.post('../contact/search.do?page=' + pageNum + "&rows=" + pageSize,searchEntity);
    };

    /**
     * 根据id查询
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../contact/findOneById.do?id=' + id);
    };

    /**
     * 回复
     * @param tableEntity
     * @returns {*}
     */
    this.reply = function (tableEntity) {
        return $http.post('../contact/reply.do', tableEntity);
    };

    /**
     * 删除联系
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../contact/deleteIds.do?ids=' + ids);
    };
});