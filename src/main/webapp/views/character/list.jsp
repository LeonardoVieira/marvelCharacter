<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<script type="text/javascript" class="init">
	

function filterGlobal () {
	$('#example').DataTable().search( 
		$('#global_filter').val(),
		$('#global_regex').prop('checked'), 
		$('#global_smart').prop('checked')
	).draw();
}

function filterColumn ( i ) {
	$('#marvelTable').DataTable().column( i ).search( 
		$('#col'+i+'_filter').val(),
		$('#col'+i+'_regex').prop('checked'), 
		$('#col'+i+'_smart').prop('checked')
	).draw();
}

$(document).ready(function() {
	$('#marvelTable').DataTable( {
		"language": {
			"lengthMenu": "Exibir _MENU_ registros por página",
			"zeroRecords": "Nenhum personagem encontrado",
			"info": "Exibindo _PAGE_ de _PAGES_",
			"infoEmpty": "Nenhum personagem encontrado",
			"infoFiltered": "(foram filtrados _MAX_ personagens)",
			"search" : "Filtrar: ",
			"paginate": {
				"previous": "Anterior",
				"next": "Próximo"
			}
		}
	});

} );


	</script>

<div class="page-wrap">

	<form class="marvel_form" method="post" name="marvel_form">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

		<table id="marvelTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th width="30%">Nome</th>
					<th width="50%">Descrição</th>
					<th width="20%">Ultima Atualização</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${marvelCharacterList}" var="marvelCharacter">
					<tr>
						<td><a href="/marvelCharacter/profile?id=${marvelCharacter.id}">${marvelCharacter.name}</a></td>
						<td><a href="/marvelCharacter/profile?id=${marvelCharacter.id}">${marvelCharacter.description}</a></td>
						<td>
							<a href="/marvelCharacter/profile?id=${marvelCharacter.id}">
								<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${marvelCharacter.modified}" />
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</div>