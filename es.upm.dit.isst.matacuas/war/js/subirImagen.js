function subirImagen(input) {
	var reader = new FileReader()
    reader.onload = function (e) {
		var imageString = e.target.result;
		// HACER VISIBLE LA MINIATURA CON LA IMAGEN SUBIDA
		var miniature = document.getElementById("miniature");
		miniature.style = "";
		miniature.src = imageString;
		
		// PONER EL STRING BASE64 EN EL INPUT HIDDEN
		var hiddenInput = document.getElementById("pic");
		var base64String = imageString.split(",")[1];
		console.log(base64String);
		hiddenInput.value = base64String;
    };
    
    reader.readAsDataURL(input.files[0]);
}