<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Park Detail Page</title>
</head>
<body>
	<c:url value="/parkDetail" var="formAction" />
	<form:form id="main-content" method="POST" action="${formAction}">
		<label for="temperatureChoice">Choose temperature preference:</label>
		<select name="temperatureChoice">
			<option value="false">Fahrenheit</option>
			<option value="true">Celsius</option>
		</select>
		<input type="submit" value="Change Temp" />
	</form:form>

	<div class="park-detail-page-image">
		<c:url var="parkImg" value="/img/parks/${park.parkImage}.jpg" />
		<img src="${parkImg}">
	</div>

	<div class="park-detail-park-details-box">

		<div class="park-detail-park-details">
			<h1>${park.parkName }</h1>
			<h2>
				<em>${park.quote} -${park.quoteSource}</em>
			</h2>

			<p>${park.description }</p>

			<h4>State: ${park.state }</h4>
			<h4>Acreage: ${park.acreage }</h4>
			<h4>Elevation (in feet): ${park.elevationInFeet }</h4>
			<h4>Miles of Trail: ${park.milesOfTrail }</h4>
			<h4>Number of Campsites: ${park.numberOfCampsites }</h4>
			<h4>Climate: ${park.climate }</h4>
			<h4>Year Founded: ${park.yearFounded }</h4>
			<h4>Annual Visitor Count: ${park.annualVisitorCount }</h4>
			<h4>Number of Animal Species: ${park.numberOfAnimalSpecies }</h4>

			<c:choose>
				<c:when test="${park.entryFee=='0'}">
					<h4>Entry Fee: Free!</h4>
					<br />
				</c:when>
				<c:otherwise>
					<h4>Entry Fee: $${park.entryFee}</h4>
					<br />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="park-detail-weather-box">
		<c:forEach var="weather" items="${weather}" varStatus="loop">
			<c:url var="weatherImg"
				value="/img/weather/${weather.weatherImage}.png" />

			<c:if test="${weather.dayOfForecast=='1'}">
				<div class="park-detail-today-weather-box">
					<div class="today-box">
						<h1>Today</h1>
						<img src="${weatherImg}">


						<c:if test="${temperatureChoice == false}">
							<h2>Low: ${weather.low}F High: ${weather.high}F</h2>

						</c:if>
						<c:if test="${temperatureChoice == true}">
							<h2>Low: ${weather.lowCelsius}C High:
								${weather.highCelsius}C</h2>
						</c:if>
						<p>${weather.weatherMessage}</p>
					</div>
				</div>
			</c:if>
		</c:forEach>

		<div class="test-box">
			<c:forEach var="weather" items="${weather}" varStatus="loop">
				<c:url var="weatherImg"
					value="/img/weather/${weather.weatherImage}.png" />

				<c:if test="${weather.dayOfForecast!='1'}">
					<div class="park-detail-4day-weather-box">

						<div class="crazy-box">
							<img src="${weatherImg}">


							<c:if test="${temperatureChoice == false}">
								<h3>Low: ${weather.low}F</h3>
								<h3>High: ${weather.high}F</h3>

							</c:if>
							<c:if test="${temperatureChoice == true}">
								<h3>Low: ${weather.lowCelsius}C</h3>
								<h3>High: ${weather.highCelsius}C</h3>
							</c:if>

							<p>${weather.weatherMessage}</p>
						</div>
					</div>
				</c:if>
				<br />
			</c:forEach>
		</div>
	</div>
</body>
</html>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
