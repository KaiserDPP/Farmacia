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

					<div>${titulo}</div>
					<p>${descripcion}</p>
					<table class="table table-striped">
						<tr>
							<th>Columna</th>
							<th>Valor</th>
						</tr>
						<tr>
							<th>Id</th>
							<td>${user.username}</td>
						</tr>
						<tr>
							<th>Nombre
							</th>
							<td>${user.name}</td>
						</tr>
						<tr>
							<th>Apellido</th>
							<td>${user.surname}</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>${user.email}</td>
						</tr>
						<tr>
							<th>Habilitado</th>
							<td>${user.enabled==true ? "S�" : "No"}</td>
						</tr>
						<tr>
							<th>Contrase�a</th>
							<td>${user.password}</td>
						</tr>
						<%--  BCrypt es una funci�n de hashing de contrase�as, es decir. una funci�n unidireccional.
							No se puede desencriptar un hash BCrypt al igual que no se puede volver 
							de pollo mcnuggets al pollo original.
							Solo puede verificar que dos hashes BCrypt son los mismos, verificando as� 
							que una contrase�a suministrada coincida con la original.
							Una soluci�n t�pica a esto es enviar un enlace de restablecimiento de contrase�a 
							de un solo uso al usuario, usar preguntas secretas. Pero ser� otra histor�a...--%>

					</table>


				</div>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="campus2b.com" />
	</jsp:include>
</body>
</html>