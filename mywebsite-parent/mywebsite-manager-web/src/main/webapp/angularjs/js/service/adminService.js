app.service('adminService', function ($http) {

    /**
     * 获取登录信息
     * @returns {*}
     */
    this.getLoginInfo = function () {
        return $http.get('../admin/getLoginInfo.do');
    };

    /**
     * 根据email查询用户
     * @param email
     * @returns {*}
     */
    this.findOneByEmail = function (email) {
        return $http.get('../admin/findOneByEmail.do?email='+ email);
    };

    /**
     * 条件分页搜索
     * @param searchEntity
     * @param pageSize
     * @param pageNum
     * @returns {*}
     */
    this.search = function (searchEntity, pageSize, pageNum) {
        return $http.post('../admin/search.do?rows=' + pageSize + '&page=' + pageNum, searchEntity);
    };

    /**
     * 修改用户信息
     * @param updateEntity
     * @returns {*}
     */
    this.update = function (updateEntity) {
        return $http.post('../admin/update.do', updateEntity);
    };

    /**
     * 删除用户
     * @param ids
     * @returns {*}
     */
    this.deleteIds = function (ids) {
        return $http.get('../admin/deleteIds.do?ids=' + ids);
    };

    /**
     * 批量审核
     * @param ids
     * @param status
     * @returns {*}
     */
    this.updateStatusIds = function (ids, status) {
        return $http.get('../admin/updateStatusIds.do?ids=' + ids + '&status=' + status);
    };

    /**
     * 发送邮箱验证码
     * @param id
     * @returns {*}
     */
    this.sendPasswordEmail = function (id) {
        return $http.get('/admin/sendPasswordEmail.do?id=' + id);
    };

    /**
     * 更新用户密码
     * @param entity
     * @returns {*}
     */
    this.updatePassword = function (entity) {
        return $http.post('../admin/updatePassword.do', entity);
    };

    /**
     * 更新邮箱
     * @param entity
     * @returns {*}
     */
    this.updateEmail = function (entity) {
        return $http.post('../admin/updateEmail.do', entity);
    };

    /**
     * 发送邮箱验证码
     * @param entity
     * @returns {*}
     */
    this.sendReEmail = function (entity) {
        return $http.post('/admin/sendReEmail.do',entity);
    };

    /**
     * 登出
     * @returns {*}
     */
    this.logout = function () {
        return $http.get("/logout");
    };
});