//
function initMap() {

	var myLocation = {
		'latitude' : 45.5306332,
		'longitude' : -73.7222529,
		'url' : 'http://donnees.ville.montreal.qc.ca/dataset/00bd85eb-23aa-4669-8f1b-ba9a000e3dd8/resource/e9b0f927-8f75-458c-8fda-b5da65cc8b73/download/limadmin.json'
	};

	var mapDiv = document.getElementById('map');
	var map = new google.maps.Map(mapDiv, {
		center : {
			lat : myLocation.latitude,
			lng : myLocation.longitude
		},
		zoom : 10
	});

	map.data.loadGeoJson(myLocation.url);
	
	map.data.addListener('click', function(e){
		alert("Position : " + e.latLng + " arrondissement " + map.data.properties.NOM);
	});
}