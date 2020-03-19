app.service('userService', function ($http) {


    /**
     * 查询所有用户
     * @returns {*}
     */
    this.findAll = function () {
        return $http.get('../user/findAll.do');
    };

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @returns {*}
     */
    this.search = function (searchEntity, page, rows) {
        return $http.post('../user/search.do?page=' + page + '&rows=' + rows, searchEntity);
    };

    /**
     * 根据id查询用户
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../user/findOneById.do?id=' + id);
    };

    /**
     * 更新用户
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../user/update.do', entity);
    };


    /**
     * 添加用户
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('../user/add.do', entity);
    };

    /**
     * 批量删除
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../user/deleteIds.do?ids='+ids);
    };
});