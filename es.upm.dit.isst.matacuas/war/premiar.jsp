<%@include file="includes/cabecera1.jsp" %>	

<!-- css y scripts especiales para la pagina -->
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>	
		<script src="js/mapa.js"></script>
		
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>		
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3>Premiar conductor</h3>
					<form action="main.jsp" method="post" accept-charset="utf-8">
						<div class="form-group">
							
								<label for="matricula">Matricula</label>
								<input type="text" class="form-control" name="matricula" id="matricula" />
							
								<label for="descripcion">Raz�n para premiarle</label>
								<textarea rows="3" class="form-control" name="descripcion" 
										id="descripcion"></textarea>
							
							
								<label for="lugar">Lugar de la acci�n</label>
								<div id="mapa"></div>
								<textarea rows="1" class="form-control" name="state"
										id="state"></textarea>
								<label for="foto">Fotograf�a</label>
								<input type="file" name="pic" accept="image/*" class="btn btn-success"; capture="camera">
								<br>
								<input type="submit"
								 value="Reportar" class="btn btn-info"/>
							
						</div>
					</form>
		</div>
		
<%@include file="includes/footer.jsp" %>