//global parameters
var map;
var markers = [];
var polySelected;

//initial map parameter
var myLocation = {
		'latitude' : 45.5306332,
		'longitude' : -73.7222529,
		'url' : 'http://donnees.ville.montreal.qc.ca/dataset/00bd85eb-23aa-4669-8f1b-ba9a000e3dd8/resource/e9b0f927-8f75-458c-8fda-b5da65cc8b73/download/limadmin.json'
	};

//resize the map when the window size change
$(window).resize(function () {  
    $('#map').css('height', getParentDivHeight());
    $('#map').css('width', "100%");
}).resize();

//get the parent div width of the map
function getParentDivWidth(){
    return $("#map").parent("div").width();
}

//get the parent div height of the map 
function getParentDivHeight(){
	var height = $("#map").parent("div").height();
	var lightPropertiesHeight = $("#lightProperties").height();
	
	if(lightPropertiesHeight > 0){
		console.log("light properties height : " + height);
		height = height - lightPropertiesHeight;
	}
	console.log("map height : " + height);
    return height;
}

//get the window height
function getWindowHeight(){
	
	var h = $(window).height(),
	   offsetTop = 400;// Calculate the top offset
	
    return h - offsetTop;
}

//initialize the size of the map
$(function(){
   $('#map').css('height', getWindowHeight());
   $('#map').css('width', getParentDivWidth());
});

// Sets the map on all markers in the array.
function setMapOnAll(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
	setMapOnAll(null);
}

//reset center on the map
function center(){
    map.setCenter(new google.maps.LatLng(myLocation.latitude, myLocation.longitude));
    map.setZoom(10);
    clearMarkers();
}

//add borough on the map
function addBoroughs(){
    map.setCenter(new google.maps.LatLng(myLocation.latitude, myLocation.longitude));
    map.setZoom(10);
    map.data.loadGeoJson(myLocation.url);
    clearMarkers();
}

//initialize the google map properties
function initMap() {

	var mapDiv = document.getElementById('map');
	map = new google.maps.Map(mapDiv, {
		center : {
			lat : myLocation.latitude,
			lng : myLocation.longitude
		},
		zoom : 10
	});

	//map.data.loadGeoJson(myLocation.url);

	map.data.setStyle({
		fillColor : 'gray',
		strokeWeight : 0.5
	});

	map.data.addListener('click', function(e) {
		map.data.forEach(function(feature) {
			// console.log("Arrondissement: " + feature.getProperty("NOM") + "
			// Type: " + feature.getProperty("TYPE"));
			var geometry = feature.getGeometry();
			var coord = [];
			geometry.forEachLatLng(function(LatLng) {
				// console.log(LatLng.lat());
				// console.log(LatLng.lng());
				coord.push({
					lat : LatLng.lat(),
					lng : LatLng.lng()
				});
			});
			var poly = new google.maps.Polygon({
				paths : coord
			});
			if (google.maps.geometry.poly.containsLocation(e.latLng, poly)) {

				polySelected = poly;
				
				clearMarkers();

				//console.log("Position : " + e.latLng + " arrondissement " + feature.getProperty("NOM"));
				if (!google.maps.Polygon.prototype.getBounds) {
					google.maps.Polygon.prototype.getBounds = function() {
						var bounds = new google.maps.LatLngBounds();
						this.getPath().forEach(function(element, index) {
							bounds.extend(element);
						});
						return bounds;
					}
				}

				polySelected.setOptions({
					fillColor : 'red'
				});
				map.setCenter(polySelected.getBounds().getCenter());
				if(map.getZoom() < 11){
					map.setZoom(11);
				}
			}
		});
	});
}

google.maps.event.addDomListener(window, 'load', initMap);