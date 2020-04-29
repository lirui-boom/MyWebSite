app.service('blogCategoryService', function ($http) {


    /**
     * 查询所有分类
     * @returns {*}
     */
    this.findAll = function () {
        return $http.get('../blogCategory/findAll');
    };

    /**
     * 通过分类id查询
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('../blogCategory/findOneById?id=' + id);
    };

    /**
     * 通过分类名查询
     * @param name
     * @returns {*}
     */
    this.findOneByName = function (name) {
        return $http.get('../blogCategory/findOneByName?name=' + name);
    };

    /**
     * 添加分类
     * @param tableEntity
     * @returns {*}
     */
    this.add = function (tableEntity) {
        return $http.post('../blogCategory/add', tableEntity);
    };

    /**
     * 更新分类
     * @param tableEntity
     * @returns {*}
     */
    this.update = function (tableEntity) {
        return $http.post('../blogCategory/update', tableEntity);
    };

    /**
     * 删除分类
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../blogCategory/deleteIds?ids=' + ids);
    };

});