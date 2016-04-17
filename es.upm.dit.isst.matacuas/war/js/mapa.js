var mapa;
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
	mapa.style.height = "400px";
	mapa.style.width = document.getElementById("lugar").width;
	
	var coords = new google.maps.LatLng(lat, lon);
	var options = {
		zoom: 16,
		center: coords,
		mapTypeControl: false,
		navigationControlOptions: {
			style: google.maps.NavigationControlStyle.SMALL
		},
		mapTypeId: google.maps.MapTypeId.ROADMAP
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
