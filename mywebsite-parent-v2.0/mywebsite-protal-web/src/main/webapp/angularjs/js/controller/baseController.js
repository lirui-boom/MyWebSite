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

    $scope.GetQueryString = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);

        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };

    $scope.JumpToUrl = function (url) {
        parent.location.href = url;
    };
});