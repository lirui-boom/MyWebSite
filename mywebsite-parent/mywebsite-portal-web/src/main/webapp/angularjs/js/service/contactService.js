app.service('contactService', function ($http) {

    /**
     * 添加联系记录
     * @param entity
     * @returns {*}
     */
    this.add = function (entity) {
        return $http.post('/contact/add.do',entity);
    };

});