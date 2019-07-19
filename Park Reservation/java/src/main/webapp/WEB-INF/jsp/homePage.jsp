<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>

<c:forEach items="${allParkList}" var="park"> 

<div id="park-column">

<c:url var="parkImg" value="/img/parks/${park.parkImage}.jpg" />
<c:url var="parkCode" value="/parkDetail"> <c:param value="${park.parkCode}" name="parkCode" /> </c:url>


<div id="park-info-image-box">
<a href="${parkCode}"> <img id="park-info-image" src="${parkImg}"> </a>
</div>

<div id="park-info-box">

<div id="park-info">
<h1>${park.parkName}</h1><br>
<p>${park.description}</p>
</div>

</div>

</div>
</c:forEach>


</body>
</html>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
