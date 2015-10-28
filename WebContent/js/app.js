/**
 * 
 */

var app = angular.module('pae', []);
app.controller('messagesCtrl', function($scope,$http) {
    $scope.messages = [{"msg_id":"1","msg":"hola","createDate":""}
                       ]
    $http.get("http://localhost:9080/LoginExample/api/messages")
    .success(function(response) {$scope.messages = response;});
});