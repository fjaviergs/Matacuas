<%@include file="includes/cabecera1.jsp" %>

<!-- css y scripts especiales para la pagina -->
		<link rel="stylesheet" type="text/css" href="css/detalle.css">
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/mapa_detalle.js"></script>
		
<%@include file="includes/cabecera2.jsp" %>						
				
			<%@include file="includes/menu.jsp" %>	
		

	</div>
	<!-- MAIN -->
	<div class="container" id="main_div">
		<h3 class="titulo">Reporte <c:if test="${reporte.esPositivo == false}">negativo </c:if><c:if test="${reporte.esPositivo == true}">positivo </c:if>sobre <c:out value="${reporte.matricula}"/></h3>
		<div id="mapa" class="col-md-12"></div>
		<div class="container" id="allInfo">
		<div class="col-md-6" id="img">
			<img src='<c:out value="${imagen}"/>' class="foto">
		</div>
		<div class="col-md-6" id="info">
			<div class="descripcion" id="descripcion">
			<h4>Informacion</h4>
			<p id="lugar">Lugar: <c:out value="${reporte.lugar}"/></p>
			<p><c:out value="${reporte.descripcion}"/></p>
			</div>
			<fieldset class="form-group" id="botones">
				<button type="button" class="btn btn-info" onclick="location.href='/main'">Volver</button>
				<c:if test="${defendible == true }"><c:if test="${reporte.esPositivo == false}"><button type="button" class="btn btn-success" onclick="location.href='respuesta.jsp'">Defenderse</button></c:if></c:if>
			</fieldset>
		</div></div></div>
<%@include file="includes/footer.jsp" %>