app.service('uploadImgService', function ($http) {

    //图片上传
    this.uploadPic = function () {

        var formData = new FormData();
        formData.append("file", file.files[0]);

        return $http({
            method: 'POST',
            url: "../uploadImg",
            data: formData,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity

        });
    };

});