<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>National Park Geek</title>
<c:url value="/css/nationalparkgeek.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logoSrc" />
		<a href="${homePageHref}"> <img src="${logoSrc}"
			alt="National Park Geek logo" />
		</a>
	</header>
	<nav id="nav-bar">
		<p class="nav-item">
			<a href="/m3-java-capstone/">Home</a>
		</p>
		<p class="nav-item">
			<a href="/m3-java-capstone/parkSurvey">Survey</a>
		</p>
	</nav>