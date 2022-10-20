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
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-lg-4">
					<fieldset>
						<legend>${titulo}</legend>
						<p>${descripcion}</p>
						<form:form action="save" method="post" modelAttribute="product">
							<div class="mb-3">
								<form:label path="title" cssClass="form-label">Título<span
										class="asterix">*</span>
								</form:label>
								<form:input id="title" path="title" cssClass="form-control" onmouseover="pinta('title')" onmouseout="vuelve('title')"/>
								<form:errors path="title" cssClass="error" />
								<form:hidden path="id" />
								<form:hidden path="productDetails.id" />
							</div>
							<div class="mb-3">
								<form:label path="code" cssClass="form-label">Código<span
										class="asterix">*</span>
								</form:label>
								<form:input id="code" path="code" cssClass="form-control" onmouseover="pinta('code')" onmouseout="vuelve('code')"/>
								<form:errors path="code" cssClass="error" />
							</div>

							<div class="mb-3">
								<form:label path="productDetails.issue_year"
									cssClass="form-label">Año fabricación</form:label>
								<form:input id="issue_year" path="productDetails.issue_year"
									cssClass="form-control" onmouseover="pinta('issue_year')" onmouseout="vuelve('issue_year')"/>
								<form:errors path="productDetails.issue_year"
									cssClass="error" />
							</div>

							<div class="mb-3">
								<form:label path="productDetails.expire_year"
									cssClass="form-label">Año cons. preferente</form:label>
								<form:input id="expire_year" path="productDetails.expire_year"
									cssClass="form-control" onmouseover="pinta('expire_year')" onmouseout="vuelve('expire_year')"/>
								<form:errors path="productDetails.expire_year" cssClass="error" />
							</div>

							<div class="mb-3">
								<form:label path="productDetails.units_number" cssClass="form-label">Número de unidades</form:label>
								<form:input id="units_number" path="productDetails.units_number"
									cssClass="form-control" onmouseover="pinta('units_number')" onmouseout="vuelve('units_number')"/>
								<form:errors path="productDetails.units_number" cssClass="error" />
							</div>

							<div class="mb-1">
								<label for="category" class="form-label">Categoría<span
									class="asterix">*</span></label>
								<form:select id="category" path="category" cssClass="form-control" items="${categories}" itemValue="category_name" itemLabel="category_name" onmouseover="pinta('category')" onmouseout="vuelve('category')" />
<%-- 									<form:option value="" label="Seleccionar categoría" /> --%>
<%-- 									<c:forEach var="cat" items="${categories}">										 --%>
<%-- 											<option value="${cat.category_name}" --%>
<%-- 												label="${cat.category_name}" ${cat.category_name == book.category.category_name ? 'selected="selected"' : ''} /> --%>
<%-- 									</c:forEach> --%>
<%-- 								</form:select> --%>
								<form:errors path="category" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>
				<div class="col-lg-8">

					<fieldset>
						<legend>Lista de medicamentos</legend>
						<table class="table table-striped">
							<tr>
								<th width="28%">Título</th>
								<th width="22%">Código</th>
								<th width="9%">Categoría</th>
								<th width="11%">Producido</th>
								<th width="11%">Cons pref</th>
								<th width="11%">Unidades</th>
								<th colspan="2" width="8%">Acciones</th>

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
										<td>${product.category.category_name}</td>
										<td>${product.productDetails.issue_year}</td>
										<td>${product.productDetails.expire_year}</td>
										<td>${product.productDetails.units_number}</td>
										<td colspan="2"><a href="${edit}"
											title="Actualizar ${product.title} con id ${product.id}">
												<button type="submit" class="btn btn-success btn-sm">M</button>
										</a><a href="${delete}"
											title="Borrar ${product.title} con id ${product.id}">
												<button type="submit" class="btn btn-danger btn-sm"
													onclick="if(!(confirm('¿Seguro que quieres eliminar el registro?'))) return false">X</button>
										</a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${how_many == 0}">
								<tr>
									<td colspan="8">La tabla no contiene registros</td>
								</tr>
							</c:if>

						</table>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
     
        function pinta(id){
            document.getElementById(id).style.backgroundColor = "#d2d2d2";
        }
        
        function vuelve(id){
            document.getElementById(id).style.backgroundColor = "WHITE";
        }
     
     
    </script>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="campus2b.com" />
	</jsp:include>
</body>
</html>