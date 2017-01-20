camViewerApp.controller('boroughCtrl', function($scope, $http) {
	$scope.getBoroughs = function() {
		$http.get('/CamViewer/data/borough/infos').success(function(response) {
			$scope.boroughs = response;
		}).error(function() {
			// handle error
		})
	};
});