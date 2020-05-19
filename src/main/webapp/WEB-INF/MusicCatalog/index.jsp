<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Music Catalog</title>
<!-- Really simple classless CSS stylesheet, see: https://github.com/oxalorg/sakura -->
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<header>
		<a href="/">Home</a>
		<a href="/artist">Artists</a>
	</header>
	<br/>
	
	<p>Search an artist</p>
	<form action="artist" method="get">
		<input name="name" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Search" />
	</form>
	<p>Add an artist</p>
	<form method="post">
		<input name="name" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Add to list" />
	</form>
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>Artist</th>
				<th>Album</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ artists }" var="artist" varStatus="loop">
				<tr>
					<td><c:out value="${loop.count }" /></td>
					<td><a href="/artist?artistId=${artist.getKey().getId() }"><c:out value="${artist.getKey().getName() }" /></a></td>
					<td><c:out value="${artist.getValue() }" /></td>
					<!-- todo: poista artisti (tai ehkä vain artistin omalta sivulta..) -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>