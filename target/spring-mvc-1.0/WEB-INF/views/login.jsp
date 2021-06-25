<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
</head>
<body>
	<%-- <div id="login">
		<!-- <h3 class="text-center text-white pt-5">Login form</h3> -->
		<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6" >
					<c:if test="${param.incorrectAccount != null}">
						<div class="alert alert-danger" role="alert">
								Username or password is incorrect
						</div>
					</c:if>
					<c:if test="${param.accessDenied != null}">
						<div class="alert alert-danger" role="alert">
								You are not authorized
						</div>
					</c:if>
					<div id="login-box" class="col-md-12">
						<form id="formLogin" class="form" action="/j_spring_security_check" method="POST">
							<h3 class="text-center text-info">Đăng nhập</h3>
							<div class="form-group">
								<label for="username" class="text-info">Tên đăng nhập:</label><br>
								<input type="text" name="j_username" id="userName"
									class="form-control" placeholder="Tên đăng nhập">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Mật khẩu:</label><br>
								<input type="password" name="j_password" id="password" class="form-control" placeholder="Mật khẩu" >
							</div>
							<div class="form-group">
								<input type="submit"name="submit" class="btn btn-info btn-md" value="Đăng nhập">
								<label for="remember-me" class="text-info"><span><a href="<c:url value ='/trang-chu'/>" class="text-info">Trở về trang chủ</a></span></label>					
							</div> 
							 <div id="register-link" class="text-right">
								<a href="<c:url value ='/dang-ky'/>" class="text-info">Bạn chưa có tài khoản ?</br></a>
							</div> 
						</form>
					</div>
				</div>
			</div>
		</div> --%>
		<div id="content" class="flex" style="padding-left: 35% ;padding-top : 10%">
			<div class="signing-logo" style="position:relative ; left:10% ; display: block;">	
				<a href="<c:url value ='/trang-chu'/>">	 
	        		<img src="https://i.imgur.com/8sX7uiv.png"width="25%">
	        	</a>
	        </div>
	        <br>
	        <div class="page-content page-container" id="page-content">
	        	
	            <div class="padding">
	            	
	                <div class="row">
	                    <div class="col-md-6">
	                        <div class="card">
	                            <div class="card-header"><strong>Đăng nhập</strong></div>
	                            <div class="card-body">
	                            <c:if test="${param.incorrectAccount != null}">
		                            <div id="alert" class="alert alert-danger alert-dismissible">Username or password is incorrect
										<button type="button" class="close" data-dismiss="alert">&times;</button>
									</div>
								</c:if>
								<c:if test="${param.accessDenied != null}">
									<div id="alert" class="alert alert-danger alert-dismissible">You are not authorized
										<button type="button" class="close" data-dismiss="alert">&times;</button>
									</div>
								</c:if>
	                                <form id="formLogin" class="form" action="/j_spring_security_check" method="POST">
	                                    <div class="form-group"><label class="text-muted" for="exampleInputEmail1">Tên đăng nhập</label><input type="text" class="form-control" name="j_username" id="userName" placeholder="Tên đăng nhập."> <small id="emailHelp" class="form-text text-muted"></small></div>
	                                    <div class="form-group"><label class="text-muted" for="exampleInputPassword1">Mật khẩu</label><input type="password" class="form-control" name="j_password" id="password"  placeholder="Nhập mật khẩu"> <small id="passwordHelp" class="form-text text-muted"></small></div>
	                                    <div class="form-group">
	                                    </div>
	                                </form>
	                                 <button id="btnSubmit" type="submit" class="btn btn-primary">Submit</button>  <a href="<c:url value ='/dang-ky'/>"><button type="button" class="btn btn-secondary btn-sm">Đăng ký</button></a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	</div>
	<br>
	<br>
	<script>
		$(document).ready(function(){
			function trim(str){
				return str.replace(/^\s+|\s+$/,'');
			}
			$('#btnSubmit').click(function(){
				if(trim($('#userName').val()).length <1)
					{
						Swal.fire({
							  icon: 'error',
							  title: 'Lỗi',
							  text: 'Bạn chưa nhập tên đăng nhập !'
							})
					}else if(trim($('#password').val()).length < 1)
						{
						Swal.fire({
							  icon: 'error',
							  title: 'Lỗi',
							  text: 'Bạn chưa nhập mật khẩu !'
							})
						}else {
							$('#formLogin').submit();
						}
			});
		});
	</script>
</body>
</html>