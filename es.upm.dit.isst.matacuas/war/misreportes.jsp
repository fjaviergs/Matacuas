<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	

	<div class="container row col-md-6 col-md-offset-3">			
			<h3>Mis reportes recibidos</h3>	
					
			<c:if test="${reportes != null }">
			<p>Numero de reportes recibidos: <c:out value="${fn:length(reportes)}"/></p>
			 <table class="table table-hover">
    <thead>
      <tr>
      	<th>Matricula</th>
        <th>Lugar</th>
        <th>Descripción</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${reportes}" var="reporte">
      <tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>
      <td><c:out value="${reporte.matricula}"/></td>
        <td><c:out value="${reporte.lugar}"/></td>
        <td><c:out value="${reporte.descripcion}" /></td>
        <td><form action="/detalle" method="post">
								<input type="hidden" name="id" id="id" value="${reporte.id}">								
								<input type="submit" value="Ampliar" class="btn btn-primary"/>							
					</form>
<%--         <a class="btn btn-primary" href="<c:url value="/detalle?id=${reporte.id}"/>">Ampliar</a></td> --%>
      </tr>
		</c:forEach>
    </tbody>
  </table> 
			
			</c:if>
	</div>	
			
<%@include file="includes/footer.jsp" %>