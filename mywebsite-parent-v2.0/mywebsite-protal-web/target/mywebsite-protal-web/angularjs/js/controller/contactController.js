app.controller('contactController', function ($scope, $controller, contactService, userService) {

    $controller('baseController', {$scope: $scope});


    $scope.contact = {};

    $scope.entity = {'email':null};

    $scope.findOneByEmail = function (email) {

        RemoveDisable();

        if (email == null || email == '') {
            $scope.entity = {};
            return;
        }

        userService.findOneByEmail(email).success(function (data) {

            if (data.status == 200) {
                $scope.entity = data.data;
                ChangeDisable(true);
            }else {
                if ($scope.entity.email != null && $scope.entity.email != '') {
                    var email = $scope.entity.email;
                    $scope.entity = {};
                    $scope.entity.email = email;
                }


            }

        });
    };

    $scope.add = function () {
        if ($scope.entity.id == null) {
            userService.add($scope.entity).success(function (data) {

                if (data.status == 200) {

                    $scope.entity = data.data;

                    $scope.addContact();

                } else {
                    alert(data.msg);
                }
            });
        } else {
            $scope.addContact();
        }
    };

    $scope.addContact = function () {

        if ($scope.entity.id == null || $scope.contact.message == null) {
            alert("参数不合法");
            return;
        }

        $scope.contact.uid = $scope.entity.id;

        contactService.add($scope.contact).success(function (data) {

            if (data.status == 200) {
                alert("您的信息已发送，请等待回复。");
            }else {
                alert(data.msg);
            }

        });
    };
});