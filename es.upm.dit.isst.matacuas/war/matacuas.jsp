<%@include file="includes/cabecera1.jsp" %>

<!-- css y scripts especiales para la pagina -->
		<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=true"></script>	
		<script src="js/mapa.js"></script>
		<script src="js/subirImagen.js"></script>
		<script src="js/reiniciarValores.js"></script>
		<link rel="stylesheet" type="text/css" href="css/reporte.css" />
		
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>
		
		<%@include file="includes/avisoerror.jsp" %>			
				
		<div class="container" id="contenido">
		<h3 id="titulo">Reportar Matacuás</h3>
		<div class="col-md-12" id="mapa"></div>
		<div class="col-md-12" id="formularioReporte">	
			<!-- en el form necesito  -->
			<form action="/reporte" method="post" accept-charset="utf-8">
				<div class="col-md-6">
				<input type="hidden" name="positivo" id="positivo" value="false">
				<fieldset class="form-group">
					<label for="lugar">Lugar del incidente</label>
					<textarea readonly rows="1" class="form-control" name="lugar" id="lugar"></textarea>
					<small class="text-muted">Selecciona un lugar en el mapa.</small>
				</fieldset>
				<fieldset class="form-group">
					<label for="matricula">Matricula</label>
					<input type="text" class="form-control" name="matricula" id="matricula" value="Introduce la matricula" onclick="ponerBlanco(this)"/>
				</fieldset>
				<fieldset class="form-group">
					<label for="foto">Fotografía</label>	
					<!-- NUEVO PARA SUBIR IMAGEN -->
					<input type="hidden" name="pic" id="pic" value="">
					<input type="file" onchange="subirImagen(this)" name="upload" id="upload" accept="image/*" class="btn btn-success" value="Selecciona una imagen"; capture="camera">							
					<!-- FIN -->
				</fieldset>
				<br>
				</div>
				<div class="col-md-6">
				<fieldset class="form-group">
					<label for="descripcion">Descripción del incidente</label>
					<textarea rows="3" class="form-control" name="descripcion" id="descripcion" value="Descripcion del inccidente" onclick="ponerBlanco(this)"></textarea>
				</fieldset>
				<img src="" alt="ImagenSubida" height="70" style="display: none;" id="miniature"/>
				<small class="text-muted" id="avisoImagen" style="display: none;">Se guardara esta imagen.</small>
				<fieldset class="form-group">
					<input type="submit" value="Reportar" class="btn btn-info" id="botonReporte"/>
					<input type="button" value="Reiniciar valores" class="btn btn-warning" id="botonReset"  onclick="reiniciarValores()"/>
					<a href="/main" class="btn btn-danger" role="button">Volver</a>
				</fieldset>
			</form>
			</div>
		</div></div>
		
<%@include file="includes/footer.jsp" %>