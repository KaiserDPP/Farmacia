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
				<div class="col">
					<fieldset>
						<legend>${titulo}</legend>
						<p>${descripcion}</p>
						<form:form action="save" method="post" modelAttribute="product">
							<div class="mb-3">
								<form:label path="title" cssClass="form-label">Título</form:label>
								<form:input path="title" cssClass="form-control" />
								<form:errors path="title" cssClass="error" />
								<form:hidden path="id" />
							</div>
							<div class="mb-3">
								<form:label path="code" cssClass="form-label">Autor</form:label>
								<form:input path="code" cssClass="form-control" />
								<form:errors path="code" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>
				<div class="col">

					<fieldset>
						<legend>Lista de medicamentos</legend>
						<table class="table table-striped">
							<tr>
								<th>Título</th>
								<th>Autor</th>
								<th>Actualizar</th>
								<th>Borrar</th>

							</tr>
							<c:if test="${how_many > 0}">
								<c:forEach items="${products}" var="product">
									<c:url var="delete" value="delete">
										<c:param name="id" value="${product.id}" />
									</c:url>
									<c:url var="edit" value="edit">
										<c:param name="id" value="${product.id}" />
									</c:url>
									<tr>
										<td>${product.title}</td>
										<td>${product.code}</td>
										<td><a href="${edit}"
											title="Actualizar ${product.title} con id ${product.id}">
												<button type="submit" class="btn btn-success">Modificar</button>
										</a></td>
										<td><a href="${delete}"
											title="Borrar ${product.title} con id ${product.id}">
												<button type="submit" class="btn btn-danger"
													onclick="if(!(confirm('¿Seguro que quieres eliminar el registro?'))) return false">Borrar</button>
										</a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${how_many == 0}">
								<tr>
									<td colspan="4">La tabla no contiene registros</td>
								</tr>
							</c:if>

						</table>
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