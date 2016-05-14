var main_div;
var info_div;
var map;
// ESTILO PARA EL MAPA
var styleArray = [{"elementType":"geometry","stylers":[{"hue":"#ff4400"},{"saturation":-68},{"lightness":-4},{"gamma":0.72}]},{"featureType":"road","elementType":"labels.icon"},{"featureType":"landscape.man_made","elementType":"geometry","stylers":[{"hue":"#0077ff"},{"gamma":3.1}]},{"featureType":"water","stylers":[{"hue":"#00ccff"},{"gamma":0.44},{"saturation":-33}]},{"featureType":"poi.park","stylers":[{"hue":"#44ff00"},{"saturation":-23}]},{"featureType":"water","elementType":"labels.text.fill","stylers":[{"hue":"#007fff"},{"gamma":0.77},{"saturation":65},{"lightness":99}]},{"featureType":"water","elementType":"labels.text.stroke","stylers":[{"gamma":0.11},{"weight":5.6},{"saturation":99},{"hue":"#0091ff"},{"lightness":-86}]},{"featureType":"transit.line","elementType":"geometry","stylers":[{"lightness":-48},{"hue":"#ff5e00"},{"gamma":1.2},{"saturation":-23}]},{"featureType":"transit","elementType":"labels.text.stroke","stylers":[{"saturation":-64},{"hue":"#ff9100"},{"lightness":16},{"gamma":0.47},{"weight":2.7}]}]
var geocoder;
var reportes;
var activeInfoWindow;
var reportesBuscados;
var markers = [];


$(document).ready(function() {
	scrollUp();
	window.addEventListener("scroll", function(evt) {
		document.getElementById("btnGoTop").style.display = "block";
		if (evt.pageY == 0) {
			document.getElementById("btnGoTop").style.display = "none";
		}
	});
	reportes = [];
	getReportes();
	geocoder = new google.maps.Geocoder;
	main_div = document.getElementById("mapa_main");
	info_div = document.getElementById("info");
	if (navigator.geolocation) {
       navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		alert("Problemas con la geolocalización");
		// Pasar a la version de lista
	}
});

function showPosition(position){
	var lat = position.coords.latitude;
	var lon = position.coords.longitude;
	crearMapa(lat, lon);
}

function crearMapa(lat, lon) {
	main_div.style.width = "100%";
	main_div.style.height = "85vh";
	
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
	map = new google.maps.Map(main_div, options);
	
	var marker = new google.maps.Marker({
		position: coords, 
		map: map,
		icon: 'https://maps.google.com/mapfiles/ms/icons/blue-dot.png'
	});
	
	var infowindow = new google.maps.InfoWindow({
			content: "Tu estás aquí."
	});
	
	marker.addListener('click', function() {
		if(activeInfoWindow != null){	
			activeInfoWindow.close();
		}
		infowindow.open(map, marker);
		activeInfoWindow = infowindow;
	});
	
	addAllMarkers();
	
	google.maps.event.addListener(map, "click", function(event) {
		if(activeInfoWindow != null){	
			activeInfoWindow.close();
		}
	});
}
/*
 * 	Genera un marcador en el mapa segun los parametros indicados:
 * 	lugar: String con la direccion donde se va a poner el nombre con formato:
 *  	calle, numero, CP municipio, provincia, pais
 * 	esPositivo: true(marker verde)/false(marker rojo)
 */
function addMarker(lugar, esPositivo, reporteID) {
	var icono_url;
	var tipo;
	var stringTipo;
	if (esPositivo == "true") {
		icono_url = 'https://maps.google.com/mapfiles/ms/icons/green-dot.png';
		tipo = "positivo";
		stringTipo = "Buen conductor ";
	} else if (esPositivo == "false"){
		icono_url = 'https://maps.google.com/mapfiles/ms/icons/red-dot.png';
		tipo = "negativo";
		stringTipo = "Matacuas ";
	}
	
	geocoder.geocode({
		"address": lugar
	}, function(results) {
		if (results == null) {
			return;
		}
		var marker = new google.maps.Marker({
			position: results[0].geometry.location,
			map: map,
			icon: icono_url
		});	
		var contentString = '<div id="content">'+
							'<p>'+stringTipo+'en '+lugar+'.</p>'+
							'<form action="/detalle" method="post">'+
								'<input type="hidden" name="id" id="id" value="'+Number(reporteID)+'" />'+
								'<input type="submit" value="Ver detalles" class="btn btn-primary"/>'+
							'</form>'+
							'</div>';
		var infowindow = new google.maps.InfoWindow({
			content: contentString
		});
		
		
		marker.addListener('click', function() {
			if(activeInfoWindow != null){	
				activeInfoWindow.close();
			}
			infowindow.open(map, marker);
			activeInfoWindow = infowindow;
		});
		markers.push(marker);
	});	
}

function getReportes() {
	var n = 0;
	while (true) {
		if (document.getElementById("reporte"+n) == null) {
			break;
		}
		reportes[n] = document.getElementById("reporte"+n).value;
		n++;
	}
}

function addAllMarkers() {
	for(var n in reportes) {
		var reporte = reportes[n].split(":");
		addMarker(reporte[0], reporte[1], reporte[2]);
	}
}

function filtrarResultados(filtro) {
	// Se cojen solo los marcadores buscados por matrícula
	var matricula = filtro.value;
	reportesBuscados = [];
	for(var r in reportes) {
		mat = reportes[r].split(":")[3];
		if (mat == matricula) {
			console.log("coinciden!");
			reportesBuscados.push(reportes[r]);
		}
	}

	// Limpiar aqui todos los marker
	deleteAllMarkers();
	// Poner toodos los marker con la busqueda
	for(var n in reportesBuscados) {
		var reporte = reportesBuscados[n].split(":");
		addMarker(reporte[0], reporte[1], reporte[2]);
	}
}

function limpiarFiltro() {
	// Se vuelven a pintar todos los marcadores en el mapa
	deleteAllMarkers();
	addAllMarkers();
	document.getElementById("filtroBusqueda").value = "Buscar matricula.";
}

function deleteAllMarkers() {
	for (var m in markers) {
		markers[m].setMap(null);
	}
	markers = [];
}

function borrarValor(e) {
	e.value = "";
}

function scrollDown() {
	document.getElementById("btnGoTop").style.display = "block";
	var $target = $('html,body'); 
	$target.animate({scrollTop: $target.height()}, "slow");
	return false;
}

function scrollUp() {
	$("html, body").animate({ scrollTop: 0 }, "slow");
	document.getElementById("btnGoTop").style.display = "none";
	return false;
}

