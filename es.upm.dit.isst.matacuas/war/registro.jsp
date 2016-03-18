<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3> Registro de usuario</h3>
					<form action="main.jsp" method="post" accept-charset="utf-8">
						<div class="form-group">
							
								<label for="nombre">Nombre</label>
								<input type="text" class="form-control" name="nombre" id="nombre" />

								<label for="apellidos">Apellidos</label>
								<input type="text" class="form-control" name="apellidos" id="apellidos" />

								<label for="usuario">Nombre de usuario</label>
								<input type="text" class="form-control" name="usuario" id="usuario" />

								<label for="password">Contraseña</label>
								<input type="password" class="form-control" name="password" id="password" />

								<label for="password2">Repita la contraseña</label>
								<input type="password2" class="form-control" name="password2" id="password2" />

								<label for="matricula">Matricula(opcional)</label>
								<input type="text" class="form-control" name="matricula" id="matricula" />
							
								<br>
								<input type="submit"
								 value="Registrarse" class="btn btn-info"/>
							
						</div>
					</form>
		</div>

<%@include file="includes/footer.jsp" %>