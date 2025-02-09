<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>
	<c:out value="${artist.getTitle() }" default="Artist"/>
	</title>
<!-- Really simple classless CSS stylesheet, see: https://github.com/oxalorg/sakura -->
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<header>
	<div>
		<a href="/">Home</a>
	</div>
	</header>
	<br/>
	<div>
	<p>Search an artist or album:</p>
	<form action="search" method="get">
		<input name="keyword" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Search" />
	</form>
	</div>
	<c:if test="${ artist != null}">
	<h3><c:out value="${artist.getTitle() }" /></h3>
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
					<td><a href="${ album.getUrl() }"><c:out value="${ album.getTitle() }" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</c:if>
</body>
</html>