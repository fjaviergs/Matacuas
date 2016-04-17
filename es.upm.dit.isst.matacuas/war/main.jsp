<%@include file="includes/cabecera1.jsp" %>

		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/mapa_main.js"></script>

<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	
			
		<div class="container row col-md-6 col-md-offset-3" id="main_div">
			
			<div class="reporte" id="mapa_main"></div>
			<div id ="info"></div>

			<c:set var="count" value="0" />
			<c:forEach items="${reportes}" var="reporte">
				<input id="reporte${count}" type="hidden" value="${reporte.lugar}:${reporte.esPositivo}:${reporte.id}" />
				<c:set var="count" value="${count + 1}" />
			</c:forEach>

		
			
<%@include file="includes/footer.jsp" %>