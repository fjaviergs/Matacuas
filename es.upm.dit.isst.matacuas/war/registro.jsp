<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>

<c:if test="${mensajeError != null }">
<div class="container row col-md-6 col-md-offset-3">
	<div class="alert alert-warning alert-dismissible text-center" role="alert">
	${mensajeError}
	</div>
</div>
	</c:if>
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3> Registro de usuario</h3>
					<form action="/registro" method="post" accept-charset="utf-8">
						<div class="form-group">
						
						
								<label for="nusuario">Nombre de usuario*</label>
								<input type="text" class="form-control" name="nusuario" id="nusuario" />
							
								<label for="nombre">Nombre</label>
								<input type="text" class="form-control" name="nombre" id="nombre" />

								<label for="apellidos">Apellidos</label>
								<input type="text" class="form-control" name="apellidos" id="apellidos" />

								<label for="password">Contraseņa*</label>
								<input type="password" class="form-control" name="password" id="password" />

								<label for="password2">Repita la contraseņa*</label>
								<input type="password" class="form-control" name="password2" id="password2" />

								<label for="matricula">Matricula(opcional)</label>
								<input type="text" class="form-control" name="matricula" id="matricula" />
							
								<br>
								<input type="submit"
								 value="Registrarse" class="btn btn-info"/>
							
						</div>
					</form>
		</div>

<%@include file="includes/footer.jsp" %>