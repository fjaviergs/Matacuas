<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>
		
<%@include file="includes/avisoerror.jsp" %>
				
		<div class="container row col-md-6 col-md-offset-3">
					<form action="/login" method="post" accept-charset="utf-8">
						<div class="form-group">
							
								<label for="name">Nombre de usuario</label>
								<input type="text" class="form-control" name="nusuario" id="nusuario" /></td>
							
								<label for="password">Contraseña</label>
								<input type="password" class="form-control" name="password" id="password" /></td>
							
								<br>
								<input type="submit"
								 value="Acceder" class="btn btn-info"/>
								<a href="/registro" class="btn btn-info" role="button">Registrarse</a>
							
						</div>
					</form>
		</div>
		
<%@include file="includes/footer.jsp" %>