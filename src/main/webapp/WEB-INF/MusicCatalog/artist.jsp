<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>
	Artists
	<c:if test="${ artist != null}"> - ${artist.getName() }</c:if>
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
	<p>Search an artist</p>
	<form action="artist" method="get">
		<input name="name" required type="text" placeholder="type name here..."/>
		<input type="submit" value="Search" />
	</form>
	<table>
		<thead>
			<tr>
				<th>${artist.getName() }</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><!--Tähän albumi 1.  --></td>
			</tr>
		<!-- todo: poista artisti -->
		</tbody>
	</table>
</body>
</html>