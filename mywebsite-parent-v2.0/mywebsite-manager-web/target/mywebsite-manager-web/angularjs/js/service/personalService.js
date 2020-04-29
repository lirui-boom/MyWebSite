app.service('personalService', function ($http) {

    /**
     * 查询个人信息
     * @returns {*}
     */
    this.findOne = function () {
        return $http.get('../personal/find');
    };

    /**
     * 修改个人信息
     * @param entity
     * @returns {*}
     */
    this.update = function (entity) {
        return $http.post('../personal/update',entity);
    };
});