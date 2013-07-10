<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Url Creation</title>
    <link type="text/css" rel="stylesheet" href="<url value="/WEB-INF/form.css"/>" />
    <header>
        <a href="/3SUN_SupLink_Paris_166000/login">logout</a>
        <label>"${sessionScope.sessionClient.email}"</label>
    </header>
</head>
<body>
<form method="post" action="home">
    <fieldset>
        <legend>New url</legend>
        <p>
        <label for="nameUrl">Initial Url</label>
        <input type="text" id="nameUrl" name="nameUrl" value="${newUml.initialUml}" size="20" maxlength="60"/>
        </p>
        <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${newUml.nom }" size="20" maxlength="60"/>
        </p>
        <p>
        <label for="url">url:	</label>
        <tr>
        <label id="url" name="url">${newUml.shortUml }</label>
        </tr>
        </p>
        <input type="submit" value="Convert" class="sansLabel" />
        </fieldset>
		<table>
			<tr>
				<th>Name</th>
				<th>OriginalUrl</th>
				<th>Shortened Url</th>
				<th>Clicks</th>
			</tr>
			
			<forEach items="${ sessionScope.sessionClient.shortUmls }" var="listUml" varStatus="boucle">
			<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair' }">
				<td><out value="${ listUml.nom }"/></td>
				<td><out value="${ listUml.shortUml }"/></td>
				<td><out value="${ listUml.initialUml }"/></td>
				<td><out value="${ listUml.numberOfUse }"/></td>	
			</tr>
			</forEach>
			</table>

</form>
</body>
</html>