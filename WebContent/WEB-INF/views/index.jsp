<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>
	<br>
	<div style="text-align:center">
		<h2>
			Hey You..!! This is your 1st Spring MCV Tutorial..<br> <br>
		</h2>
		<h3>
			<a href="welcome">Click here to See Welcome Message... </a>(to
			check Spring MVC Controller... @RequestMapping("/")) <c:url var="logoutUrl" value='/logout'/>
        <form action="${logoutUrl}" method="post">
			  <input type="submit" value="Log out" />
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</h3>
	</div>
</body>
</html>