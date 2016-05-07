<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	
			
			<%@include file="includes/menu.jsp" %>
			
			<%@include file="includes/avisoinfo.jsp" %>	

	<div class="container row col-md-12">
	<div class="container row col-md-10 col-md-offset-1">			
			<h3>Usuarios registrados</h3>	
			<c:if test="${recibidos != null }">
			<p>Número de usuarios registrados: <c:out value="${fn:length(usuarios)}"/></p>
			 <table class="table table-hover">
    <thead>
      <tr>
      	<th>GoogleID</th>
        <th>Email</th>
        <th>Matrícula</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${usuarios}" var="usuario">
      <tr <c:choose><c:when test="${reporte.esPositivo == false}">class="danger"</c:when><c:when test="${reporte.esPositivo == true}">class="success"</c:when></c:choose>>
      <td><c:out value="${usuario.googleID}"/></td>
        <td><c:out value="${usuario.email}"/></td>
        <td><c:out value="${usuario.matricula}" /></td>
        <td><form action="/adminusers" method="post">
								<input type="hidden" name="id" id="id" value="${usuario.googleID}">								
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