<%@page import="java.util.List"%>
<%@page import="com.project.sportyshoes.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboad</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
</head>
<body>
	<h5>${message }</h5>
	<h2 class="m-2">Hii ${admin.name} welcome to Sporty shoe</h2>

	<div class="card ml-auto fixed-top" style="width: 15rem">
		<div class="card-body">
			<h6 class="card-title">${admin.name }</h6>
			<h6 class="card-title">${admin.email }</h6>
			<a href="#" data-toggle="modal" data-target="#exampleModalLong"
				class="text-primary">Change Password</a> <a href="/admin/logout"
				class="text-danger">Logout</a>
		</div>
	</div>

	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Admin
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="model-body" style="padding: 10px">
					<form action="/admin/changePassword" method="post">
						<div class="form-group">
							<label for="oldPassword">Old Password</label> <input type="text"
								name="oldPassword" required="true"
								placeholder="Enter Old Password" id="oldPassword"
								class="form-control" />
						</div>
						<div class="form-group">
							<label for="password">New Password</label> <input type="password"
								name="newPassword" required="true"
								placeholder="Enter new password" id="password"
								class="form-control" />
						</div>
						<div class="form-group">
							<input type="submit" value="Change Password"
								class="btn btn-primary btn-block" />
						</div>
						<div class="form-group">
							<input type="button" value="Close"
								class="btn btn-secondary btn-block" data-dismiss="modal" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<h2 class="m-2 mt-5 mb-3 d-inline-block">Category</h2>
	<button type="button" class="btn btn-primary btn-sm mb-2"
		data-toggle="modal" data-target="#addCategory">Add Category
		&plus;</button>

	<div class="modal fade" id="addCategory" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Add
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="model-body" style="padding: 10px">
					<form action="/category/create" method="post">
						<div class="form-group">
							<label for="category_name">Name</label> <input type="text"
								required="true" name="name" id="category_name"
								placeholder="Enter Name" class="form-control" />
						</div>
						<div class="form-group">
							<label for="desc">Description</label> <input type="text"
								name="discription" required="true"
								placeholder="Enter description" id="desc" class="form-control" />
						</div>
						<div class="form-group">
							<input type="submit" value="Add Category"
								class="btn btn-primary btn-block" />
						</div>
						<div class="form-group">
							<input type="button" value="Close"
								class="btn btn-secondary btn-block" data-dismiss="modal" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container mt-10 table-responsive">
		<table class="table table-hover">
			<tr>
				<th>Category Id</th>
				<th>Category Name</th>
				<th>Category Description</th>
				<th>Action</th>
			</tr>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>${category.discription}</td>
					<td><a href="/category/delete/${category.id}" class="text-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<h2 class="m-2 mb-3 d-inline-block">Products</h2>
	<button type="button" class="btn btn-primary btn-sm mb-2"
		data-toggle="modal" data-target="#addProduct">Add Products
		&plus;</button>

	<div class="modal fade" id="addProduct" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Add
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="model-body" style="padding: 10px">
					<form action="/product/create" method="post">
						<div class="form-group">
							<label for="product_name">Name</label> <input type="text"
								required="true" name="name" id="product_name"
								placeholder="Enter Name" class="form-control" />
						</div>
						<div class="form-group">
							<label for="desc">Description</label> <input type="text"
								name="discription" required="true"
								placeholder="Enter description" id="desc" class="form-control" />
						</div>
						<div class="form-group">
							<label for="category">Category</label> <select required="true"
								name="category_name" class="form-control">
								<option value="">Choose the category</option>
								<c:forEach var="category" items="${categories}">
									<option value="${category.name}">${category.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="price">Price</label> <input type="number"
								name="price" required="true" placeholder="Enter price"
								id="price" class="form-control" />
						</div>

						<div class="form-group">
							<input type="submit" value="Add Product"
								class="btn btn-primary btn-block" />
						</div>
						<div class="form-group">
							<input type="button" value="Close"
								class="btn btn-secondary btn-block" data-dismiss="modal" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="container mt-10 table-responsive">
		<table class="table table-hover">
			<tr>
				<th>Product Id</th>
				<th>Shoe Name</th>
				<th>Shoe Description</th>
				<th>Shoe price</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.discription}</td>
					<td>${product.price}</td>
					<td><a href="/product/update/${product.id}">Update</a></td>
					<td><a href="/product/delete/${product.id}" class="text-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<h2 class="m-2 mb-3">Users</h2>
	<div class="container mt-10 table-responsive">

		<table class="table table-hover">
			<tr>
				<th>User Id</th>
				<th>User Name</th>
				<th>User Email</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<h2 class="m-2 mb-3">Purchases</h2>
	<div class="container mt-10 d-flex justify-content-between">
		<form action="/admin/filter/category">
			<div class="form-inline m-2 ml-10">
				<label for="category" class="mr-2">Filter by Category : </label> <select
					required="true" name="category_name" class="form-control">
					<option value="">Choose the category</option>
					<c:forEach var="category" items="${categories}">
						<option value="${category.name}">${category.name}</option>
					</c:forEach>
				</select> <input type="submit" value="Filter Category"
					class="btn btn-primary ml-2" />
				<c:if test="${filteredPurchases != null }">
					<a href="/admin/dashboard" class="btn btn-danger ml-2">Clear Filter</a>
				</c:if>
			</div>
		</form>
	</div>
	<div class="container mt-10 table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Order Id</th>
					<th>User name</th>
					<th>Product name</th>
					<th>Product Category</th>
					<th>Product Price</th>
					<th>Purchased Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${filteredPurchases == null }">
					<c:forEach var="purchase" items="${purchases}">
						<tr>
							<td>${purchase.purchase_id }</td>
							<td>${purchase.user.name }</td>
							<td>${purchase.product.id}</td>
							<td>${purchase.product.category.name}</td>
							<td>${purchase.product.price}</td>
							<td>${purchase.ordered_date}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${filteredPurchases != null }">
					<c:forEach var="purchase" items="${filteredPurchases}">
						<tr>
							<td>${purchase.purchase_id }</td>
							<td>${purchase.user.name }</td>
							<td>${purchase.product.id}</td>
							<td>${purchase.product.category.name}</td>
							<td>${purchase.product.price}</td>
							<td>${purchase.ordered_date}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
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

