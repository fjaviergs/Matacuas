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
		<h3 class="titulo">Reporte en Calle Gran V�a, 27, 28013 Madrid</h3>
		<div id="mapa"></div>
		<div id="img">
			<img src="img/otro.jpg" class="foto">
		</div>
		<div class="descripcion" id="descripcion">
			<p>Se define como accidente (del latín accĭdens, -entis) a cualquier suceso que es provocado por una acción violenta y repentina ocasionada por un agente externo involuntario, y que da lugar a una lesión corporal. La amplitud de los términos de esta definición obliga a tener presente que los diferentes tipos de accidentes se hallan condicionados por múltiples fenómenos de carácter imprevisible e incontrolable.</p>
			<p>La causa inmediata de un accidente puede ser la falta de equipo de protección, pero la causa básica puede ser que el equipo de protección no se utilice porque resulta incómodo.1 Supongamos que a un tornero se le ha clavado una viruta en un ojo. Investigado el caso se comprueba que no llevaba puestas las gafas de seguridad. La causa inmediata es la ausencia de protección individual, pero la causa básica está por descubrirse y es fundamental investigar por qué no llevaba puestas las gafas. Podría ser por tratar de ganar tiempo, porque no estaba especificado que en aquel trabajo se utilizaran gafas (falta de normas de trabajo), porque las gafas fueran incómodas.</p>
		</div>
		<div class="botones">
			<button type="button" class="btn btn-info" onclick="location.href='main.jsp'">Volver</button>
			<button type="button" class="btn btn-success" onclick="location.href='respuesta.jsp'">Responder</button>
		</div>
		
<%@include file="includes/footer.jsp" %>