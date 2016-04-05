
<div class="container col-md-9">
		<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Menú principal
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
  <li><a href="main.jsp">Pagina principal</a></li>
      <li role="separator" class="divider"></li>
    <li><a href="matacuas.jsp">Reportar Matacuás</a></li>
    <li><a href="premiar.jsp">Premiar conductor</a></li>
    <li><a href="/perfil">Modificar perfil</a></li>
    <li><a href="/misreportes">Consultar mis reportes</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="${urlLogOut}">Cerrar sesión</a></li>
  </ul>
	</div>
</div>
<div class="container col-md-3">
<c:if test="${email != null }">
	<div class="list-group-item list-group-item-info text-center">
	Logueado con ${email}
	</div>
</c:if>
</div>

