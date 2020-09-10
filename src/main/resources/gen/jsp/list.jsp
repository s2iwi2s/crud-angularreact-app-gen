<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crude Template for XYCLASSYX</title>
<link rel="stylesheet"
	href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<header>
		<h1>XYCLASSYX - List</h1>
	</header>
	<hr>

	<c:set var="alertClass" value="alert-info"/>
	<c:if test="${ALERT_MSG.type == 'INFO'}"><c:set var="alertClass" value="alert-success"/></c:if>
	<c:if test="${ALERT_MSG.type == 'WARNING'}"><c:set var="alertClass" value="alert-warning"/></c:if>
	<c:if test="${ALERT_MSG.type == 'ERROR'}"><c:set var="alertClass" value="alert-danger"/></c:if>
	
	<p class="${alertClass}">${ALERT_MSG.message}</p>

	<a href="/XYclassYX" role="button" class="">NEW XYCLASSYX</a><br/>
	<c:forEach var="pageItem" items="${XYclassYXList}">
	<ul class="pagination pagination-sm">
		<li class="page-item"><a class="page-link" href="/XYclassYX/List?page=0">First</a></li>
		<c:forEach var="i" begin="2" end="${pageItem.totalPages - 1}">
		<li class="page-item"><a class="page-link" href="/XYclassYX/list?page=${i - 1}">${i}</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="/XYclassYX/list?page=${pageItem.totalPages - 1}">Last</a></li>
	</ul>
	<hr>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>#</th>
XYjsp-list-header-tmplYX
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageItem.content}">
					<tr>
						<td><a href="/XYclassYX/${item.id}?page=${page}">${item.id}</a></td>
XYjsp-list-fields-tmplYX
						<td>
							<a class="btn" href="/XYclassYX/${item.id}?page=${page}"><i class="icon-edit"></i> Edit</a>
							<a class="btn" href="/XYclassYX/delete/${item.id}?page=${page}"><i class="icon-delete"></i> Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<hr>
	<ul class="pagination pagination-sm">
		<li class="page-item"><a class="page-link" href="/XYclassYX/list?page=0">First</a></li>
		<c:forEach var="i" begin="2" end="${pageItem.totalPages - 1}">
		<li class="page-item"><a class="page-link" href="/XYclassYX/list?page=${i - 1}">${i}</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="/XYclassYX/list?page=${pageItem.totalPages - 1}">Last</a></li>
	</ul>
	</c:forEach>
	<a href="/XYclassYX" role="button" class="">NEW XYCLASSYX</a>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
	
</body>
</html>