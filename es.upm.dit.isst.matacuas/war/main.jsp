<%@include file="includes/cabecera1.jsp" %>

		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/mapa_main.js"></script>

<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	
			
		<div id="main_div">
			
			<div class="reporte" id="mapa_main"></div>

			<c:set var="count" value="0" />
			<c:forEach items="${reportes}" var="reporte">
				<input id="reporte${count}" type="hidden" value="${reporte.lugar}:${reporte.esPositivo}:${reporte.id}:${reporte.matricula}" />
				<c:set var="count" value="${count + 1}" />
			</c:forEach>
			
			<div class="container row col-md-4 col-md-offset-4" id="busqueda">
				<input type="text" cols="7" class="form-control" id="filtroBusqueda" onchange="filtrarResultados(this)" value="Matricula + Enter"/>
				<input type="button" class="btn btn-info" id="btnLimpiarFiltro" onclick="limpiarFiltro()" value="Limpiar Filtro"/>
			</div>

		
			
<%@include file="includes/footer.jsp" %>