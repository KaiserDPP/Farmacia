<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>
</head>
<body class="d-flex flex-column h-100 text-center">
	<jsp:include page="/WEB-INF/view/templates/nav.jsp"></jsp:include>
	<div class="flex-shrink-0">
		<div class="container">
			<div class="row">
				<div class="col-8  mx-auto">
					<h2 class="text-center mb-4">${titulo}</h2>
					<p class="text-start mb-4">${descripcion}</p>
					<p class="text-start mb-4">${descripcion_larga}</p>
					<img class = "img-fluid" src="${pageContext.request.contextPath}/assets/img/fachada-farmacia.jpg" >
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="campus2b.com" />
	</jsp:include>
</body>
</html>