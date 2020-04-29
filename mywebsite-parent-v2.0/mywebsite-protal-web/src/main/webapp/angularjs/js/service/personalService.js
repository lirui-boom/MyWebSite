app.service('personalService', function ($http) {

    /**
     * 查询个人信息
     * @returns {*}
     */
    this.findOne = function () {
        return $http.get('../personal/find');
    };

});