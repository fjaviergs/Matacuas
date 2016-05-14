var styleArray = [{"elementType":"geometry","stylers":[{"hue":"#ff4400"},{"saturation":-68},{"lightness":-4},{"gamma":0.72}]},{"featureType":"road","elementType":"labels.icon"},{"featureType":"landscape.man_made","elementType":"geometry","stylers":[{"hue":"#0077ff"},{"gamma":3.1}]},{"featureType":"water","stylers":[{"hue":"#00ccff"},{"gamma":0.44},{"saturation":-33}]},{"featureType":"poi.park","stylers":[{"hue":"#44ff00"},{"saturation":-23}]},{"featureType":"water","elementType":"labels.text.fill","stylers":[{"hue":"#007fff"},{"gamma":0.77},{"saturation":65},{"lightness":99}]},{"featureType":"water","elementType":"labels.text.stroke","stylers":[{"gamma":0.11},{"weight":5.6},{"saturation":99},{"hue":"#0091ff"},{"lightness":-86}]},{"featureType":"transit.line","elementType":"geometry","stylers":[{"lightness":-48},{"hue":"#ff5e00"},{"gamma":1.2},{"saturation":-23}]},{"featureType":"transit","elementType":"labels.text.stroke","stylers":[{"saturation":-64},{"hue":"#ff9100"},{"lightness":16},{"gamma":0.47},{"weight":2.7}]}]

// Crea el mapa para el ejemplo solamente
/*$(document).ready(function() {
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
OLD
*/
// Genera el mapa para la vista del detalla de un reporte
// Toma la direccioón de la primera linea de la descripc-
// ion del suceso, la convierte a coordenadas y centra el
// mapa en ese punto.
//
// Se supone que la primera linea de la descripcion tiene
// el siguiente formato:
// <div class="descripcion" id="descripcion">
//			<p id="lugar">Lugar: DIRECCION REAL</p>

var geocoder;

$(document).ready(function() {
	
	// String con la direccion
	var lugar = document.getElementById("lugar").innerHTML;
	var direccion = lugar.substr(7, lugar.length);
	
	// Busqueda de las coordenadas de la direccion
	geocoder = new google.maps.Geocoder;
	geocoder.geocode({
		"address": direccion
	}, function(results) {
		generarMapa(results[0].geometry.location);
	});
});

function generarMapa(coords) {
	var mapa = document.getElementById("mapa");
	mapa.style.width = "100%";
	mapa.style.height = "40vh";
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
}