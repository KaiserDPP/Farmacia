<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet" type="text/css" />

</head>
<body class="d-flex flex-column h-100 text-center">
	<div class="flex-shrink-0">
		<section class="h-100 gradient-form" style="background-color: #eee;">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-xl-10">
						<div class="card rounded-3 text-black">
							<div class="row g-0">
								<div class="col-lg-6">
									<div class="card-body p-md-5 mx-md-4">
										<div class="text-center">
											
											<h4 class="mt-1 mb-5 pb-1">${title}</h4>
										</div>
										<form:form
											action="${pageContext.request.contextPath}/autorizado"
											method="post">
											<p>Conectate para acceder a tu cuenta.</p>

											<div class="form-outline mb-3">
												<label class="form-label" for="form2Example11">Usuario</label><input
													type="text" name="username" id="form2Example11"
													class="form-control"
													placeholder="Nombre de usuario que usaste al crear la cuenta" />
											</div>

											<div class="form-outline mb-3">
												<label class="form-label" for="form2Example22">Contraseña</label><input
													type="password" name="password" id="form2Example22"
													class="form-control" />
											</div>

											<div class="text-center pt-1 mb-3 pb-1">
												<input type="submit"
													class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
													value="Validar" /><br /> <a class="text-muted" href="#!">He
													olvidado mi contraseña</a><br />
												<c:if test="${param.error != null}">
													<span class="error">Usuario o Contraseña incorrectos</span>
												</c:if>
												<c:if test="${param.logout != null}">
													<span class="information">¡¡Hasta pronto!!</span>
												</c:if>
												<c:if test="${param.error == null}">
													<br />
												</c:if>
											</div>
											<div
												class="d-flex align-items-center justify-content-center pb-4">
												<p class="mb-0 me-2">Crear una nueva cuenta</p>
												<button type="button" class="btn btn-outline-danger">Crear
													cuenta</button>
											</div>
										</form:form>
									</div>
								</div>
								<div
									class="col-lg-6 d-flex align-items-center gradient-custom-2">
									<div class="text-white px-3 py-4 p-md-5 mx-md-4">
										<h2 class="mb-4" >BBDD PHARMACIA</h2>
										<p class="small mb-0">${description}<br />
										</p>
										<img class = "img-fluid"
												src="${pageContext.request.contextPath}/assets/img/fondo_login.jpg" >
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>







	<!-- 		<div class="container"> -->
	<!-- 			<div class="row"> -->
	<!-- 				<div class="col-6 mx-auto"> -->
	<!-- 					<div class="logo"></div> -->
	<!-- 					<div class="login-block"> -->
	<!-- 						<h1>Login</h1> -->
	<%-- 						<form:form action="${pageContext.request.contextPath}/autorizado" --%>
	<%-- 							method="post"> --%>
	<!-- 							<div class="mb-3"> -->
	<!-- 								Nombre de usuario <input type="text" name="username" -->
	<!-- 									class="form-control" /> -->
	<!-- 							</div> -->
	<!-- 							<div class="mb-3"> -->
	<!-- 								Contraseña <input type="password" name="password" -->
	<!-- 									class="form-control" /> -->
	<!-- 							</div> -->
	<!-- 							<input type="submit" class="btn btn-primary" value="Validar" /> -->
	<%-- 						</form:form> --%>
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
</body>
</html>