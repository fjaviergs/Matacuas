var main_div;
var info_div;
var map;
var geocoder;
var reportes;

$(document).ready(function() {
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
	main_div.style.height = "500px";
	
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
	map = new google.maps.Map(main_div, options);
	
	var marker = new google.maps.Marker({
		position: coords, 
		map: map,
		icon: 'https://maps.google.com/mapfiles/ms/icons/blue-dot.png'
	});
	
	marker.addListener('click', function() {
		info_div.innerHTML ='<p>Tu estas ahi</p>';
	});
	
	addAllMarkers();
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
	if (esPositivo == "true") {
		icono_url = 'https://maps.google.com/mapfiles/ms/icons/green-dot.png';
		tipo = "positivo";
	} else if (esPositivo == "false"){
		icono_url = 'https://maps.google.com/mapfiles/ms/icons/red-dot.png';
		tipo = "negativo";
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
		marker.addListener('click', function() {
			// boton para entrar al detalle
			var form ='<form action="/detalle" method="post"><input type="hidden" name="id" id="id" value="'+Number(reporteID)+'"><input type="submit" value="Ampliar" class="btn btn-primary"/></form>';
			info_div.innerHTML ='<p>Reporte '+tipo+'</p>'+form;
		});
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