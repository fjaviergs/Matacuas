
<div class="container col-md-9">
		<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Men� principal
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="matacuas.jsp">Reportar Matacu�s</a></li>
    <li><a href="premiar.jsp">Premiar conductor</a></li>
    <li><a href="perfil.jsp">Modificar perfil</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="login.jsp">Cerrar sesi�n</a></li>
  </ul>
	</div>
</div>
<div class="container col-md-3">
<c:if test="${nUsuario != null }">
	<div class="list-group-item list-group-item-info text-center">
	Logueado como ${nUsuario}
	</div>
</c:if>
</div>

