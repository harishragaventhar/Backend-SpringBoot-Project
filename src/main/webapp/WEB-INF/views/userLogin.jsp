<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up user</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
</head>
<body>
	<c:if test="${message != null}">
		<div class="alert alert-${type } alert-dismissible">
			<button class="close" type="button" data-dismiss="alert">&times;</button>
			${message }
		</div>
	</c:if>
	<div
		class="mt-5 d-flex flex-column align-items-center justify-content-conter">
		<h2>User Login</h2>
		<div class="container w-25">
			<form action="/user/authenticate" method="post">
				<div class="form-group">
					<label for="email">Email</label> <input type="email" name="email"
						required="true" placeholder="Enter email" id="email"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						name="password" required="true" placeholder="Enter password"
						id="password" class="form-control" />
				</div>
				<div class="form-group">
					<input type="submit" value="Login"
						class="btn btn-primary btn-block" />
				</div>
				<p class="text-center">Or</p>
				<div class="form-group">
					<a href="/home" type="submit" value="Login"
						class="btn btn-secondary btn-block" > Home</a>
				</div>
			</form>
		</div>
	</div>




	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>
</html>