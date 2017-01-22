camViewerApp.controller('boroughCtrl', function($scope, $http) {
	$scope.getBoroughs = function() {
		$http.get('/CamViewer/data/borough/infos').success(function(response) {
			$scope.boroughs = response;
		}).error(function() {
			// handle error
		})
	};
	
	$scope.showBorough = function(codeId) {
		for(i =0; i < $scope.boroughs.length; i++){
			if($scope.boroughs[i].codeId == codeId){
				console.log("Selected Borough : " + $scope.boroughs[i].nom);
				var coord = [];
				for(j = 0; j < $scope.boroughs[i].coordinates.length; j++){
					// console.log(LatLng.lat());
					// console.log(LatLng.lng());
					coord.push({
						lat : $scope.boroughs[i].coordinates[j][1],
						lng : $scope.boroughs[i].coordinates[j][0]
					});
				}
				var poly = new google.maps.Polygon({
					paths : coord
				});
				
				selectedPolyOnMap(poly);
				
			}
		}
	}
});