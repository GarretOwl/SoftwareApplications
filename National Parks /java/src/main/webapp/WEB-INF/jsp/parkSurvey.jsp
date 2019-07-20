<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Survey Page</title>
</head>
<body>

<h1> Welcome to the National Park Survey!</h1>


<c:url value="/parkSurvey" var="formAction" />
		<form:form id = "main-content" method="POST" action="${formAction}" modelAttribute="newSurvey">
			<label for="parkCode">Choose your favorite Park: </label>
			
			<select name ="parkCode">
			<option value = "CVNP"> Cuyahoga Valley National Park </option>
						<option value = "ENP"> Everglades National Park </option>
									<option value = "GCNP"> Grand Canyon National Park </option>
											<option value = "GNP"> Glacier National Park </option>
													<option value = "GSMNP"> Great Smoky Mountains National Park </option>
															<option value = "GTNP"> Grand Teton National Park </option>
																	<option value = "MRNP"> Mount Rainier National Park </option>
						<option value = "RMNP"> Rocky Mountain National Park </option>
						<option value = "YNP"> Yellowstone National Park </option>
						<option value = "YNP2"> Yosemite National Park </option>
			</select>		
			
			<br>	
			
			<label for="emailAddress">Enter your Email: </label>
			<form:input type="text" path="emailAddress" placeholder="Enter Email"/>
			<form:errors path="emailAddress"/>
			
			<br>	
	
	
	<label for="state">Choose your State: </label>		
	<select name ="state">
	<option value="AL">Alabama</option>
	<option value="AK">Alaska</option>
	<option value="AZ">Arizona</option>
	<option value="AR">Arkansas</option>
	<option value="CA">California</option>
	<option value="CO">Colorado</option>
	<option value="CT">Connecticut</option>
	<option value="DE">Delaware</option>
	<option value="DC">District Of Columbia</option>
	<option value="FL">Florida</option>
	<option value="GA">Georgia</option>
	<option value="HI">Hawaii</option>
	<option value="ID">Idaho</option>
	<option value="IL">Illinois</option>
	<option value="IN">Indiana</option>
	<option value="IA">Iowa</option>
	<option value="KS">Kansas</option>
	<option value="KY">Kentucky</option>
	<option value="LA">Louisiana</option>
	<option value="ME">Maine</option>
	<option value="MD">Maryland</option>
	<option value="MA">Massachusetts</option>
	<option value="MI">Michigan</option>
	<option value="MN">Minnesota</option>
	<option value="MS">Mississippi</option>
	<option value="MO">Missouri</option>
	<option value="MT">Montana</option>
	<option value="NE">Nebraska</option>
	<option value="NV">Nevada</option>
	<option value="NH">New Hampshire</option>
	<option value="NJ">New Jersey</option>
	<option value="NM">New Mexico</option>
	<option value="NY">New York</option>
	<option value="NC">North Carolina</option>
	<option value="ND">North Dakota</option>
	<option value="OH">Ohio</option>
	<option value="OK">Oklahoma</option>
	<option value="OR">Oregon</option>
	<option value="PA">Pennsylvania</option>
	<option value="RI">Rhode Island</option>
	<option value="SC">South Carolina</option>
	<option value="SD">South Dakota</option>
	<option value="TN">Tennessee</option>
	<option value="TX">Texas</option>
	<option value="UT">Utah</option>
	<option value="VT">Vermont</option>
	<option value="VA">Virginia</option>
	<option value="WA">Washington</option>
	<option value="WV">West Virginia</option>
	<option value="WI">Wisconsin</option>
	<option value="WY">Wyoming</option>
</select>
			<br>
			
			<label for="activityLevel">What's your activity level?</label>
			
			<input type="radio" name="activityLevel" value="inactive" checked> Inactive
				<input type="radio" name="activityLevel" value="sedentary" checked> Sedentary
					<input type="radio" name="activityLevel" value="active" checked> Active
						<input type="radio" name="activityLevel" value="extremely-active" checked> Extremely Active
			
  		
  			
  			
			
			<br>
			
			
			
			<input type="submit" value="Vote!" />
		</form:form>




</body>
</html>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
