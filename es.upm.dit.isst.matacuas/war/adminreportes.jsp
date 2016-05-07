<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	

	<div class="container row col-md-12">
	<div class="container row col-md-10 col-md-offset-1">			
			<h3>Reportes recibidoss</h3>	
			<c:if test="${recibidos != null }">
			<p>N�mero de reportes recibidos: <c:out value="${fn:length(reportes)}"/></p>
			 <table class="table table-hover">
    <thead>
      <tr>
      	<th>Matr�cula</th>
        <th>Lugar</th>
        <th>Descripci�n</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${reportes}" var="reporte">
      <tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>
      <td><c:out value="${reporte.matricula}"/></td>
        <td><c:out value="${reporte.lugar}"/></td>
        <td><c:out value="${reporte.descripcion}" /></td>
        <td><form action="/adminreportes" method="post">
								<input type="hidden" name="id" id="id" value="${reporte.id}">								
								<input type="submit" value="Eliminar" class="btn btn-primary"/>							
					</form>
<%--         <a class="btn btn-primary" href="<c:url value="/detalle?id=${reporte.id}"/>">Ampliar</a></td> --%>
      </tr>
		</c:forEach>
    </tbody>
  </table> 
			
			</c:if>
	</div>
	
	</div>	
			
<%@include file="includes/footer.jsp" %>