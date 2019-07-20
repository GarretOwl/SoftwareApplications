<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Survey Results Page</title>
</head>
<body>

<div class = "survey-page-greeting">
<h1> Welcome to Survey Results!</h1>
<p> See how the various state parks of America are ranked in survey popularity</p>
</div>


<c:forEach var="survey" items="${surveys}" varStatus="loop">

<%-- <c:url var="parkImg" value="/img/parks/${survey.parkImage}.jpg" />
<img src="${parkImg}"> --%>

<h1> ${survey.key } : <em>${survey.value} surveys submitted!</em></h1>



</c:forEach>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
