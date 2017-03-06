<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style type="text/css">


</style>

<form:form class="marvel_form" commandName="marvelCharacter"
		action="/marvelCharacter/list" method="post" name="marvel_form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	<div id="ProfilePage">
		<div id="LeftCol">
			<div id="Photo">
				<img src="${marvelCharacter.thumbnail.path}/standard_large.${marvelCharacter.thumbnail.extension}" alt="${marvelCharacter.name}" height="140" width="140">
			</div>
		</div>

		<div id="Info">
			<p>
				<input readonly="true" type="text" value="${marvelCharacter.name}" style="width: 80%;">
			</p>
			<p>
				<textarea readonly="true" cols="70" rows="5">${marvelCharacter.description}</textarea>
				<br/><br/>
				<input type="button" class="btn btn-lg btn-primary btn-block" value="Voltar" onClick="history.go(-1)" style="width: 15%;"> 
			</p>
		</div>

		<div style="clear:both"></div>
	</div>

	<h1>Fascículos</h1>
	<hr style="height:1px; border:none; color:#000; background-color:#000; margin-top: 0px; margin-bottom: 0px; width: 90%;"/>


	<div id="ProfilePage">
		<c:forEach var="comic" items="${comics}">
			<div id="LeftCol">
				<div id="Photo">
					<img src="${comic.thumbnail.path}/portrait_medium.${marvelCharacter.thumbnail.extension}" alt="${comic.title}" height="150" width="100"> <br/> <br/> <br/>
				</div>
			</div>

			<div id="Info">
				<p>
					<strong style="width: 50px;">Título: </strong> ${comic.title}
					<strong style="width: 150px;">número da capa: </strong>${comic.digitalId}
				</p>
				<p>
					<textarea readonly="true" cols="70" rows="5">${comic.description}</textarea>
				</p>
			</div>

			<div style="clear:both"></div>
			<br/><br/>
			<hr style="height:1px; border:none; color:#000; background-color:#000; margin-top: 0px; margin-bottom: 0px; width: 100%;"/>
			<br/><br/>
		</c:forEach>

	</div>
</form:form>