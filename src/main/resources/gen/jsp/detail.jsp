<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crude Template for XYCLASSYX - <c:if
		test="${empty XYclassYXDetail.id}">NEW</c:if> <c:if
		test="${not empty XYclassYXDetail.id}">UPDATE</c:if>
</title>
<link rel="stylesheet"
	href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<header>
		<h1>
			XYCLASSYX - Details -
			<c:if test="${empty XYclassYXDetail.id}">NEW</c:if>
			<c:if test="${not empty XYclassYXDetail.id}">UPDATE</c:if>
		</h1>
	</header>
	<hr>
	<c:if test="not empty ALERT_MSG">
		<c:set var="alertClass" value="alert-info"/>
		<c:if test="${ALERT_MSG.type == 'INFO'}"><c:set var="alertClass" value="alert-success"/></c:if>
		<c:if test="${ALERT_MSG.type == 'WARNING'}"><c:set var="alertClass" value="alert-warning"/></c:if>
		<c:if test="${ALERT_MSG.type == 'ERROR'}"><c:set var="alertClass" value="alert-danger"/></c:if>
		
		<p class="${alertClass}">${ALERT_MSG.message}</p>
	</c:if>
	<div class="col-md-8 order-md-1">
		<form action="/XYclassYX/detail" method="post">
			<input type="hidden" name="id" value="${XYclassYXDetail.id}">
			<input type="hidden" name="page" value="${page}">
				
			<c:if test="${not empty XYclassYXDetail.id}"><a href="/XYclassYX/delete/${XYclassYXDetail.id}?page=${page}" role="button" class="btn">Delete</a></c:if>
			<a href="/XYclassYX/list?page=${page}" role="button" class="btn">List</a>
			<c:if test="${not empty XYclassYXDetail.id}"><a href="/XYclassYX?page=${page}" role="button" class="btn">New</a></c:if>
			<hr>
			<input type="submit" value="Submit" role="button" class="btn btn-primary">
			<div class="mb-3">
				<label for="username">ID</label>
				<div class="input-group">
					<div class="input-group-prepend">
					</div>
					<input type="text" value="${XYclassYXDetail.id}" disabled
						class="form-control"></div>
				</div>
			</div>

<!-- START-TEMPLATE-START -->
XYjsp-fields-tmplYX
<!-- END-TEMPLATE-end -->
		
			<input type="submit" value="Submit" role="button" class="btn btn-primary">
			<hr>
			<c:if test="${not empty XYclassYXDetail.id}"><a href="/XYclassYX/delete/${XYclassYXDetail.id}?page=${page}" role="button" class="btn">Delete</a></c:if>
			<a href="/XYclassYX/list?page=${page}" role="button" class="btn">List</a>
			<c:if test="${not empty XYclassYXDetail.id}"><a href="/XYclassYX?page=${page}" role="button" class="btn">New</a></c:if>
		</form>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>

</body>
</html>