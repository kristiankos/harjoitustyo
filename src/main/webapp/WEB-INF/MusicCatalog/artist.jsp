<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>
	Artists
	<c:if test="${ artist != null}"> - ${artist.getTitle() }</c:if>
	</title>
<!-- Really simple classless CSS stylesheet, see: https://github.com/oxalorg/sakura -->
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<header>
	<div>
		<a href="/">Home</a>
		<a href="/artist">Artists</a>
	</div>
	</header>
	<br/>
	<div>
	<p>Search an artist</p>
	<form action="artist" method="get">
		<input name="name" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Search" />
	</form>
	</div>
	<c:if test="${ artist != null}">
	<h2>${artist.getTitle() }</h2>
	<div>
	<table>
		<thead>
			<tr>
				<th>Albums</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ albums }" var="album">
				<tr>
					<td><c:out value="${ album.getTitle() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</c:if>
</body>
</html>