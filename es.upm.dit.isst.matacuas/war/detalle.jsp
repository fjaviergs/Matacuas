<%@include file="includes/cabecera1.jsp" %>

<!-- css y scripts especiales para la pagina -->
		<link rel="stylesheet" type="text/css" href="css/detalle.css">
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/mapa_detalle.js"></script>
		
<%@include file="includes/cabecera2.jsp" %>						
				
			<%@include file="includes/menu.jsp" %>	
		

	</div>
	<!-- MAIN -->
	<div class="container row col-md-6 col-md-offset-3" id="main_div">
		<h3 class="titulo">Reporte <c:if test="${reporte.esPositivo == false}">negativo </c:if><c:if test="${reporte.esPositivo == true}">positivo </c:if>sobre <c:out value="${reporte.matricula}"/></h3>
		<div id="mapa"></div>
		<div id="img">
			<img src="img/otro.jpg" class="foto">
		</div>
		<div class="descripcion" id="descripcion"><p>Lugar: <c:out value="${reporte.lugar}"/></p>
		<c:out value="${reporte.descripcion}"/>
		</div>
		<div class="botones">
			<button type="button" class="btn btn-info" onclick="location.href='main.jsp'">Volver</button>
			<c:if test="${defendible == true }"><c:if test="${reporte.esPositivo == false}">
			<form action="/defensa" method="post">
								<input type="hidden" name="detalle" id="detalle" value="detalle">
								<input type="hidden" name="ReporteID" id="ReporteID" value="${reporte.id}">							
								<input type="submit" value="Defenderse" class="btn btn-success"/>							
					</form>
			</c:if></c:if>
		</div>
		
<%@include file="includes/footer.jsp" %>