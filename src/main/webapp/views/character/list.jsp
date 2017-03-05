<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<script>
	$(document).ready(function() {
		$('#tableCliente').DataTable();
	});
</script>

<div class="page-wrap">

	<form class="marvel_form" action="/cliente/list" method="post" name="marvel_form">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

		<table id="tableCliente" class="display" >
				<thead>
					<tr>
						<th width="20%">Nome</th>
						<th width="60%">Descrição</th>
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
									<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${marvelCharacter.modified}" />
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</form>
</div>