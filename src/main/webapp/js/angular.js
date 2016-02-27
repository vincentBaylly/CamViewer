angular.module('site', [])

        .controller('lightCtrl', function ($scope, $http) {

            $scope.getLights = function () {
                $http.get('/CamViewer/data/geometry').success(function (response) {
                    $scope.geometry = response;

                    var latitude = $scope.geometry.latitude;
                    var longitude = $scope.geometry.longitude;

                    var mapDiv = document.getElementById('map');
                    var map = new google.maps.Map(mapDiv, {
                        center: {lat: latitude, lng: longitude},
                        zoom: 15
                    });

                    var latLng = new google.maps.LatLng(latitude, longitude);
                    var marker = new google.maps.Marker({
                        position: latLng,
                        map: map
                    });

                }).error(function () {
                    //handle error
                })
                
                $http.get('/CamViewer/data/properties').success(function (response) {
                    $scope.properties = response;
                }).error(function () {
                    //handle error
                })
            };
        });