<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>
	Search
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
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>Result</th>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ searchResults }" var="search" varStatus="loop">
				<tr>
					<td><c:out value="${loop.count }" /></td>
					<td><a href="${search.getUrl() }"><c:out value="${search.getTitle()}" /></a></td>
					<td><c:out value="${search.getClass().getSimpleName()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>