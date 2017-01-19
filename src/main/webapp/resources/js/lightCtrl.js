angular.module('site', [])

.controller('lightCtrl', function($scope, $http) {

	$scope.getLight = function() {
		$http.get('/CamViewer/data/geometry').success(function(response) {
			$scope.geometry = response;

			var latitude = $scope.geometry.latitude;
			var longitude = $scope.geometry.longitude;

			var mapDiv = document.getElementById('map');
			var map = new google.maps.Map(mapDiv, {
				center : {
					lat : latitude,
					lng : longitude
				},
				zoom : 15
			});

			var latLng = new google.maps.LatLng(latitude, longitude);
			var marker = new google.maps.Marker({
				position : latLng,
				map : map
			});

		}).error(function() {
			// handle error
		})

		$http.get('/CamViewer/data/properties').success(function(response) {
			$scope.properties = response;
		}).error(function() {
			// handle error
		})
	};

	$scope.getGeometriess = function() {
		$http.get('/CamViewer/data/geometries').success(function(response) {
			$scope.geometries = response;

			if (typeof polySelected !== "undefined") {

				clearMarkers();

				for (i = 0; i < $scope.geometries.length; i++) {

					var latitude = $scope.geometries[i].latitude;
					var longitude = $scope.geometries[i].longitude;

					var latLng = new google.maps.LatLng(latitude, longitude);

					if (google.maps.geometry.poly.containsLocation(latLng, polySelected)) {

						var marker = new google.maps.Marker({
							position : latLng,
							map : map
						});
						markers.push(marker);
					}
				}
			}

		}).error(function() {
			// handle error
		})
	};

	$scope.getLights = function() {
		$http.get('/CamViewer/data/lightsinfo').success(function(response) {
			$scope.lightsinfo = response;

			if (typeof polySelected !== "undefined") {

				clearMarkers();

				for (i = 0; i < $scope.lightsinfo.length; i++) {
					(function() {
						var latitude = $scope.lightsinfo[i].geometry.latitude;
						var longitude = $scope.lightsinfo[i].geometry.longitude;

						var latLng = new google.maps.LatLng(latitude, longitude);

						if (google.maps.geometry.poly.containsLocation(latLng, polySelected)) {

							var marker = new google.maps.Marker({
								position : latLng,
								map : map
							});

							var properties = $scope.lightsinfo[i].lightProperties;

							marker.addListener('click', function() {
								$scope.properties = properties;
								$scope.$apply();
							});

							markers.push(marker);
						}
					}());
				}
			}else{
				
			}

		}).error(function() {
			// handle error
		})
	};
});
