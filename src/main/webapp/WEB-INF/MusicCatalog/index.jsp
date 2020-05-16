<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Music Catalog</title>
</head>
<!-- Really simple classless CSS stylesheet, see: https://github.com/oxalorg/sakura -->
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<body>
	<p>Hello!</p>
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
					<td><c:out value="${artist.getKey().getName() }" /></td>
					<td><c:out value="${artist.getValue() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>