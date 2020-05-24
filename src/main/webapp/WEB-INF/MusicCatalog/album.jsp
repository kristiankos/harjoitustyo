<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>
	<c:out value="${album.getTitle() }" default="Album"/>
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

	<a href="${ album.getArtist().getUrl() }"><c:out value="${ album.getArtist().getTitle() }" /></a>
	<h3>${album.getTitle() }</h3>
	<div>
	<table>
		<thead>
			<tr>
				<th>Tracks</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ tracks }" var="track">
				<tr>
					<td><c:out value="${ track.getTitle() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>

</body>
</html>