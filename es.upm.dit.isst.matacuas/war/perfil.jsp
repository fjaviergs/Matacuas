<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>			
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3>Perfil de usuario</h3>
					<form action="main.jsp" method="post" accept-charset="utf-8">
						<div class="form-group">
							
								<label for="nombre">Nombre</label>
								<input type="text" class="form-control" name="nombre" id="nombre" />

								<label for="apellidos">Apellidos</label>
								<input type="text" class="form-control" name="apellidos" id="apellidos" />

								<label for="usuario">Nombre de usuario</label>
								<input type="text" class="form-control" name="usuario" id="usuario" />

								<label for="matricula">Matricula(opcional)</label>
								<input type="text" class="form-control" name="matricula" id="matricula" />
							
								<br>
								<input type="submit"
								 value="Actualizar" class="btn btn-info"/>
							
						</div>
					</form>
		</div>
		
<%@include file="includes/footer.jsp" %>