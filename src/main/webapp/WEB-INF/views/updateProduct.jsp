<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
</head>
<body>
	<h2 class="m-4 text-center">Update Product</h2>

	<div class="container w-50">
		<form action="/product/update" method="post">
			<div class="form-group">
				<label for="id">Id</label> <input type="text" value="${product.id}"
					name="id" readonly="readonly" id="id" class="form-control" />
				<small id="filehelp" class="form-text text-muted">You can't Edit</small>
			</div>
			<div class="form-group">
				<label for="name">Name</label> <input type="text"
					value="${product.name}" required="required" name="name" id="name"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="discription">Description</label> <input type="text"
					value="${product.discription}" name="discription" id="discription"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="category">Category</label> <select required="required"
					name="category" id="category" class="form-control">
					<option value="${product.category.id }">${product.category.name}</option>
					<c:forEach var="category" items="${categories}">
						<c:if test="${category.id != product.category.id }">
							<option value="${category.id }">${category.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="price">Price</label> <input type="number"
					name="price" value=${product.price } required="required"
					id="price" class="form-control" />
			</div>
			<div class="form-group">
				<input type="submit" value="Update"
					class="btn btn-primary btn-block" />
			</div>
		</form>
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