<%@include file="includes/cabecera1.jsp" %>
<%@include file="includes/cabecera2.jsp" %>	

		<%@include file="includes/menu.jsp" %>			
				
		<div class="container row col-md-6 col-md-offset-3">
		<h3>Responder reporte</h3>
					<form action="/defensa method="post" accept-charset="utf-8">
						<div class="form-group">
<%-- 							<input type="hidden" id="reporteID" name="reporteID" value="<c:out value="${reporte.id}"/>"/> --%>
								<input type="hidden" id="reporteID" name="reporteID" value="<c:out value="${reporteID}"/>"/>
								<label for="descripcion">Texto de la respuesta</label>
								<textarea rows="9" class="form-control" name="descripcion" 
										id="descripcion"></textarea>
							
								<input type="submit"
								 value="Enviar" class="btn btn-info"/>
							
						</div>
					</form>
		</div>

<%@include file="includes/footer.jsp" %>