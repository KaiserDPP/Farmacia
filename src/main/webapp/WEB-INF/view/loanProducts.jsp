<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>
</head>
<body class="d-flex flex-column h-100 text-center">
	<jsp:include page="/WEB-INF/view/templates/nav.jsp"></jsp:include>
	<div class="flex-shrink-0">
		<div class="container">
			<div class="row mt-4">
				<div class="col-6 mx-auto">
					<fieldset>
						<legend>${titulo}</legend>
						<p>${descripcion}</p>
						<form:form action="confirm_loan" method="post"
							modelAttribute="user">
							<div class="mb-3">
								<form:label path="name" cssClass="form-label">Nombre</form:label>
								<form:input disabled="true" path="name" cssClass="form-control" />
								<form:errors path="name" cssClass="error" />
								<form:hidden path="username" />
								<form:hidden path="name" />
								<form:hidden path="surname" />
								<form:hidden path="email" />
								<form:hidden path="password" />
								<form:hidden path="enabled" />
								<form:hidden path="confirmPassword" />
							</div>
							<div class="mb-3">
								<form:label path="email" cssClass="form-label">Email</form:label>
								<form:input disabled="true" path="email" cssClass="form-control" />
								<form:errors path="email" cssClass="error" />
								
							</div>
							<div class="mb-1">
								<label for="products" class="form-label">Medicamentos prestados</label>
								<%--Para conocer los libros prestados al usuario antes de usarlos en el FORM --%>
								<%--<c:forEach var="book" items="${user.books}">--%>
								<%--	<c:out value="${book.title}" />--%>
								<%--</c:forEach>--%>
								<form:select path="products" cssClass="form-control" multiple="true" items="${productlist}" itemValue="title" itemLabel="title">
<%-- 									<form:option value="" label="Seleccionar libro(s)" /> --%>
<%-- 									<c:forEach var="bookdispo" items="${booklist}">									 --%>
<%-- 											<option value="${bookdispo.title}" label="${bookdispo.title}"/>										 --%>
<%-- 									</c:forEach> --%>
								</form:select>
								<form:errors path="products" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="campus2b.com" />
	</jsp:include>
</body>
</html>