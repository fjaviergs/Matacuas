function reiniciarValores() {
	navigator.geolocation.getCurrentPosition(showPosition);
	document.getElementById("matricula").value="Introduce la matricula"
	document.getElementById("descripcion").value="Descripcion del incidente"
	document.getElementById("miniature").style.display="none";
	document.getElementById("avisoImagen").style.display="none";
}