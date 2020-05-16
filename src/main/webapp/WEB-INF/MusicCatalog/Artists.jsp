<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Artists</title>
</head>
<body>
	<p>Hello Artist!</p>
		<form method="post">
		<input name="name" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Add to list" />
	</form>
	<table>
		<thead>
			<tr>
				<th>Artists</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ allArtists }" var="artist">
				<tr>
					<td><c:out value="${artist.getName() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>