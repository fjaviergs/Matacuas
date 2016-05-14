var mapa;
var styleArray = [{"elementType":"geometry","stylers":[{"hue":"#ff4400"},{"saturation":-68},{"lightness":-4},{"gamma":0.72}]},{"featureType":"road","elementType":"labels.icon"},{"featureType":"landscape.man_made","elementType":"geometry","stylers":[{"hue":"#0077ff"},{"gamma":3.1}]},{"featureType":"water","stylers":[{"hue":"#00ccff"},{"gamma":0.44},{"saturation":-33}]},{"featureType":"poi.park","stylers":[{"hue":"#44ff00"},{"saturation":-23}]},{"featureType":"water","elementType":"labels.text.fill","stylers":[{"hue":"#007fff"},{"gamma":0.77},{"saturation":65},{"lightness":99}]},{"featureType":"water","elementType":"labels.text.stroke","stylers":[{"gamma":0.11},{"weight":5.6},{"saturation":99},{"hue":"#0091ff"},{"lightness":-86}]},{"featureType":"transit.line","elementType":"geometry","stylers":[{"lightness":-48},{"hue":"#ff5e00"},{"gamma":1.2},{"saturation":-23}]},{"featureType":"transit","elementType":"labels.text.stroke","stylers":[{"saturation":-64},{"hue":"#ff9100"},{"lightness":16},{"gamma":0.47},{"weight":2.7}]}]
var geocoder;

$(document).ready(function() {
	mapa = document.getElementById("mapa");
	geocoder = new google.maps.Geocoder;
	
	if (navigator.geolocation) {
       navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		alert("Problemas con la geolocalización");
	}
});

function showPosition(position) {
	var lat = position.coords.latitude;
	var lon = position.coords.longitude;
	var coords = new google.maps.LatLng(lat, lon); 
	geocoder.geocode({
			'location': coords
		  }, function (results, status) {
			if (status === google.maps.GeocoderStatus.OK) {
			  if (results[1]) {
				document.getElementById("lugar").value = results[0].formatted_address;
			  } else {
				alert('No results found');
			  }
			} else {
			  alert('Geocoder failed due to: ' + status);
			}
	  });
	crearMapa(lat, lon);
}

function crearMapa(lat, lon) {
	mapa.style.height = "40vh";
	mapa.style.width = "100%";
	
	var coords = new google.maps.LatLng(lat, lon);
	var options = {
		zoom: 16,
		center: coords,
		mapTypeControl: false,
		navigationControlOptions: {
			style: google.maps.NavigationControlStyle.SMALL
		},
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		styles: styleArray
	};
	var map = new google.maps.Map(mapa, options);
	
	var marker = new google.maps.Marker({
		position: coords, 
		map: map
	});
	
	google.maps.event.addListener(map, "click", function(event) {
		marker.setPosition(event.latLng);

		geocoder.geocode({
			'location': event.latLng
		  }, function (results, status) {
			if (status === google.maps.GeocoderStatus.OK) {
			  if (results[1]) {
				document.getElementById("lugar").value = results[0].formatted_address;
			  } else {
				alert('No results found');
			  }
			} else {
			  alert('Geocoder failed due to: ' + status);
			}
	  });
		
	});
}

function ponerBlanco(e) {
	e.value="";
}