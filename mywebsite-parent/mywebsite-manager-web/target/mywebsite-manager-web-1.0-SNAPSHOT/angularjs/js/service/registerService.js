app.service("registerService", function ($http) {

    this.register = function (entity) {

        return $http.post('../admin/add.do',entity);
    };

});