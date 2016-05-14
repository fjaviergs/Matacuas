<%@include file="includes/cabecera1.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/mapa_main.js"></script>

<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	
			
		<div class="container" id="main_1">
			<div class="jumbotron" id="jumbotron">
			<div id="busqueda" class="input-group">
				<input type="text" size="7" class="form-control" id="filtroBusqueda" onclick="borrarValor(this)" onchange="filtrarResultados(this)" value="Buscar matricula"/>
				<span class="input-group-btn">
					<input type="button" class="btn btn-info" id="btnLimpiarFiltro" onclick="limpiarFiltro()" value="Reiniciar busqueda"/>
					<input type="button" class="btn btn-success" id="btnVerTabla" onclick="scrollDown()" value="Ver ultimos eventos"/>
				</span>
			</div>
			
			<div id="mapa_main"></div>
			</div>
		</div>
			<c:set var="count" value="0" />
			<c:forEach items="${reportes}" var="reporte">
				<input id="reporte${count}" type="hidden" value="${reporte.lugar}:${reporte.esPositivo}:${reporte.id}:${reporte.matricula}" />
				<c:set var="count" value="${count + 1}" />
			</c:forEach>
			
		<div class="container" id="main_2">
			<h3>Ultimos eventos</h3>
			<!--div class="col-md-6" id="ultimosRealizados"-->
			<div id="ultimosRealizados">
				<h4>Notificaciones realizadas</h4>
				<table class="table table-hover">
				<thead>
					<th>Lugar</th>
					<th>Fecha/Hora</th>
					<th></th>
				</thead>
				<tbody>
				<c:forEach items="${realizados}" var="reporte">
				<tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>	
					<td><c:out value="${reporte.lugar}"/></td>
					<td><fmt:formatDate value="${reporte.fecha}" pattern="dd-MMM-yyyy | HH:mm"/></td>
					<td><form action="/detalle" method="post">
								<input type="hidden" name="id" id="id" value="${reporte.id}">								
								<input type="submit" value="Detalle" class="btn btn-primary"/>							
					</form>
				</tr>
				</c:forEach>
				</tbody>
				</table>
			</div>
			<!--div class="col-md-6" id="ultimosRecibidos"-->
			<div id="ultimosRecibidos">
				<h4>Notificaciones recibidas</h4>
				<table class="table table-hover">
				<thead>
					<th>Lugar</th>
					<th>Fecha/Hora</th>
					<th></th>
				</thead>
				<tbody>
				<c:forEach items="${recibidos}" var="reporte">
				<tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>	
					<td><c:out value="${reporte.lugar}"/></td>
					<td><fmt:formatDate value="${reporte.fecha}" pattern="dd/MM/yyyy HH:mm"/></td>
					<td><form action="/detalle" method="post">
								<input type="hidden" name="id" id="id" value="${reporte.id}">								
								<input type="submit" value="Detalle" class="btn btn-primary"/>							
					</form>
				</tr>
				</c:forEach>
				</tbody>
				</table>
				
			</div>
			</div>
			<input type="button" class="btn btn-success" id="btnGoTop" onclick="scrollUp()" value="Volver arriba"/>

<%@include file="includes/footer.jsp" %>