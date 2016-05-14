<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	

	<div class="container row col-md-12" id="tablasMisReportes">
	<div class="container row col-md-10 col-md-offset-1">			
		<h3 id="tituloTabla">
		Reportes recibidos
		<c:if test="${recibidos != null }">
		(<c:out value="${fn:length(recibidos)}"/>)
		</h3>	
			<table class="table table-hover">
			<thead>
			<tr>
				<th>Matrícula</th>
				<th>Lugar</th>
				<!--th>Descripción</th-->
				<th>Fecha/Hora</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${recibidos}" var="reporte">
			<tr id="filaTabla" <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>
				<td><c:out value="${reporte.matricula}"/></td>
				<td><c:out value="${reporte.lugar}"/></td>
				<!--td><c:out value="${reporte.descripcion}" /></td-->
				<td id="fecha"><fmt:formatDate value="${reporte.fecha}" pattern="dd/MM/yyyy HH:mm"/></td>
				<td><form action="/detalle" method="post">
					<input type="hidden" name="id" id="id" value="${reporte.id}">								
					<input type="submit" value="Ampliar" class="btn btn-primary"/>							
				</form></td>
			</tr>
			</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>
	
	<div class="container row col-md-10 col-md-offset-1">			
		<h3 id="tituloTabla">
		Reportes realizados
		<c:if test="${realizados != null }">
		(<c:out value="${fn:length(realizados)}"/>)
		</h3>	
		<table class="table table-hover">
		<thead>
		<tr>
			<th>Matrícula</th>
			<th>Lugar</th>
			<!--th>Descripción</th-->
			<th>Fecha/Hora</th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${realizados}" var="reporte">
		<tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>
			<td><c:out value="${reporte.matricula}"/></td>
			<td><c:out value="${reporte.lugar}"/></td>
			<!--td><c:out value="${reporte.descripcion}" /></td-->
			<td id="fecha"><fmt:formatDate value="${reporte.fecha}" pattern="dd/MM/yyyy HH:mm"/></td>
			<td><form action="/detalle" method="post">
				<input type="hidden" name="id" id="id" value="${reporte.id}">
				<input type="hidden" name="mio" id="mio" value="true">								
				<input type="submit" value="Ampliar" class="btn btn-primary"/>							
			</form></td>
		</tr>
		</c:forEach>
		</tbody>
		</table> 
		</c:if>
	</div></div>	
			
<%@include file="includes/footer.jsp" %>