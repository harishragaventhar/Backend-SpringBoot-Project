<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
</head>
<body>

	<div class="card ml-auto text-center fixed-top" data-toggle="modal"
		data-target="#exampleModalLong" style="width: 10rem">
		<div class="card-body">
			<h6 class="card-title">${user.name }</h6>
		</div>
	</div>

	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">User
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="model-body" style="padding: 10px">
					<h6>Name:${user.name}</h6>
					<h6>Name:${user.email}</h6>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<a href="/user/logout" class="btn btn-danger">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<h2 class="m-2">Hii ${user.name} welcome to Sporty shoe</h2>

	
	<h3 class="m-3">Products</h3>
	<div class="d-flex p-2">
		<c:forEach var="product" items="${products}">
			<div class="card m-2" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">${product.name}</h5>
					<h6 class="card-subtitle mb-2 text-muted">${product.category.name}</h6>
					<p class="card-text">${product.discription}</p>
					<a href="/purchase/buy/${product.id}" class="card-link btn btn-primary btn-sm">Buy Product</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<h3 class="m-3">Orders</h3>
	<div class="container mt-10 table-responsive">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>Order Id</th>
            <th>Product name</th>
            <th>Product Category</th>
            <th>Product Price</th>
            <th>Purchased Date</th>
          </tr>
        </thead>
        <tbody>
         <c:forEach var="purchase" items="${purchases}">
         	<tr>
         		<td>${purchase.purchase_id }</td>
         		<td>${purchase.product.id}</td>
         		<td>${purchase.product.category.name}</td>
         		<td>${purchase.product.price}</td>
         		<td>${purchase.ordered_date}</td>
         	</tr>
         </c:forEach>
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