app.controller('baseController',function ($scope) {


    $scope.selectIds = []; //存放已选择的id
    $scope.updateSelection = function (id) {

        if ($scope.selectIds.indexOf(id) == -1) {
            $scope.selectIds.push(id);
        } else {
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index, 1);
        }

    };

    $scope.jsonToString = function (jsonString, key) {

        if (jsonString == null || jsonString.length == 0) {
            return "";
        }

        var object = JSON.parse(jsonString);
        var value = "";

        for (var i = 0; i < object.length; i++) {

            if (i == 0) {
                value += object[i][key];
            } else {
                value += "," + object[i][key];
            }
        }

        return value;

    };

    $scope.showSuccess = function () {
        toastr.success('你刚才的操作已经成功被保存。', '成功!');
    };

    $scope.showSuccessInfo = function (info) {
        toastr.success(info, '成功!');
    };

    $scope.showInfo = function (info) {
        toastr.info(info, '信息!');
    };

    $scope.showError = function () {
        toastr.error('对不起，您的操作出现错误，请联系管理员。', '错误!');
    };

    $scope.showErrorInfo = function (info) {
        toastr.error(info, '错误!');
    };

    $scope.showWarningInfo=function (info) {
        toastr.warning(info, '警告!');
    }
});