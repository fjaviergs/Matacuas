// Crea el mapa para el ejemplo solamente
$(document).ready(function() {
	var mapa = document.getElementById("mapa");
	mapa.style.width = document.getElementById("descripcion").width;
	mapa.style.height = "250px";
	var coords = new google.maps.LatLng(40.420109, -3.702771);
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
});