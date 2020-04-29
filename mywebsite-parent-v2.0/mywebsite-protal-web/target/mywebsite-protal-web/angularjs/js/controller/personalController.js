app.controller('personalController', function ($scope, $controller, personalService) {

    $controller('baseController', {$scope: $scope});

    $scope.entity = {};

    $scope.find = function () {

        personalService.findOne().success(function (data) {

            if (data.status == 200) {

                $scope.entity = data.data;

            }else {

                $scope.showErrorInfo('信息查询失败');
            }

        });

    };

    $scope.showContent = function (id,content) {
        var div = document.getElementById(id);
        if (div == null) {
            return;
        }
        div.innerHTML = content;
    };
});