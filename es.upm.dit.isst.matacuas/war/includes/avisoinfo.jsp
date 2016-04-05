<c:if test="${mensajeInfo != null }">
<div class="container row col-md-6 col-md-offset-3">
	<div class="alert alert-info alert-dismissible text-center fade in" role="alert">
	${mensajeInfo}
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	</div>
</div>
	</c:if>