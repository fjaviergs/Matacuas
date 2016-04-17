<%@include file="includes/cabecera1.jsp" %>	

<!-- css y scripts especiales para la pagina -->
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>	
		<script src="js/mapa.js"></script>
		
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>
		
		<%@include file="includes/avisoerror.jsp" %>		
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3>Premiar conductor</h3>
					<form action="/reporte" method="post" accept-charset="utf-8">
						<div class="form-group">
								<input type="hidden" name="positivo" id="positivo" value="true">
								
								<label for="matricula">Matricula</label>
								<input type="text" class="form-control" name="matricula" id="matricula" />
							
								<label for="descripcion">Razón para premiarle</label>
								<textarea rows="3" class="form-control" name="descripcion" 
										id="descripcion"></textarea>
							
							
								<label for="lugar">Lugar de la acción</label>
								<div id="mapa"></div>
								<textarea readonly rows="1" class="form-control" name="lugar"
										id="lugar"></textarea>
								<label for="foto">Fotografía</label>
								<input type="file" name="pic" id="pic" accept="image/*" class="btn btn-success"; capture="camera">
								<br>
								<input type="submit"
								 value="Reportar" class="btn btn-info"/>
							
						</div>
					</form>
		</div>
		
<%@include file="includes/footer.jsp" %>