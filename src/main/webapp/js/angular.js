angular.module('site', [])

        .controller('lightCtrl', function ($scope, $http) {

            $scope.getLights = function () {
                $http.get('/CamViewer/data/light').success(function (response) {
                    $scope.lights = response;

                    var latitude = $scope.lights.coordinates[1];
                    var longitude = $scope.lights.coordinates[0];

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
            };
        });