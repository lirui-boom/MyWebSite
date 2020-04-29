app.service('userService', function ($http) {

    /**
     * 查询所有
     * @returns {*}
     */
    this.findAll = function () {
        return $http.get('/user/findAll');
    };
    /**
     * 根据id查询
     * @param id
     * @returns {*}
     */
    this.findOneById = function (id) {
        return $http.get('/user/findOneById?id=' + id);
    };

    /**
     * 根据邮箱地址查询用户
     * @param email
     * @returns {*}
     */
    this.findOneByEmail = function (email) {
        return $http.get('/user/findOneByEmail?email=' + email);
    };

    /**
     * 添加用户
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('/user/add', entity);
    };
});