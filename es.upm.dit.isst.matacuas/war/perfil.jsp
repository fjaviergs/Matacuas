<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>
		<%@include file="includes/avisoerror.jsp" %>			
				
		<div class="container row col-md-6 col-md-offset-3" id="mainPerfil">
		<h3>Perfil de usuario</h3>
			<form action="/perfil" method="post" accept-charset="utf-8">
				<fieldset class="form-group">
					<label for="googleID">Google ID</label>
					<input type="text" class="form-control" name="googleID" id="googleID" value = "${googleID}" readonly/>
				</fieldset>
				<fieldset class="form-group">	
					<label for="email">Email</label>
					<input type="text" class="form-control" name="email" id="email" value = "${email}" readonly/>
				</fieldset>
				<fieldset class="form-group">
					<label for="matricula">Matrícula(opcional)</label>
					<input type="text" class="form-control" name="matricula" id="matricula" value = "${miMatricula}" />
				</fieldset>			
				<fieldset class="form-group">
					<input type="submit" value="Actualizar" class="btn btn-info"/>
					<a href="/main" class="btn btn-info" role="button">Volver</a>
				</fieldset>			
			</form>
		</div>
		
<%@include file="includes/footer.jsp" %>