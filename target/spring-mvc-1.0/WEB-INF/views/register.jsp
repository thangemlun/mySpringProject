<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="userRegisterURL" value="/dang-ky" />
<c:url var="userAPI" value="/api/user" />
<!DOCTYPE html>
<html>
<head>
<title>Đăng ký</title>
</head>


<body>
<div class="card bg-light">
<article class="card-body mx-auto" style="max-width: 400px;">
	<c:if test="${not empty message}">
		<div id="alert" class="alert alert-${alert} alert-dismissible">${message}
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>
	</c:if>	
	<h4 class="card-title mt-3 text-center"> Đăng ký người dùng </h4>
	<p class="text-center">Bắt đầu tạo tài khoản</p>
	<form id="registerForm" action='<c:url value ='/dang-ky'/>' method="post">
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
		 </div>
        <input class="form-control" name="userName" placeholder="Tên đăng nhập" type="text" required="required" >
    </div> <!-- form-group// -->
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"><i class="fa fa-address-book" aria-hidden="true"></i></span>
		 </div>
        <input class="form-control" name="fullName" placeholder="Họ và Tên" type="text" required="required">
    </div> <!-- form-group// -->
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"><i class="fa fa-envelope" aria-hidden="true"></i></span>
		 </div>
        <input name="email" id="email" class="form-control" placeholder="Email " type="email" required="required">
    </div> <!-- form-group// -->
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-key" aria-hidden="true"></i> </span>
		</div>
        <input class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" type="password" required="required">
    </div> <!-- form-group// -->
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-check-square-o" aria-hidden="true"></i> </span>
		</div>
        <input class="form-control" id="passwordConfirm" name="passwordConfirm"  placeholder="Xác nhận mật khẩu " type="password">
    </div>
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"><i class="fa fa-phone" aria-hidden="true"></i></span>
		</div>
    	<input name="phonenumber" id="phonenumber" class="form-control" placeholder="Số điện thoại" type="text" required="required" >
    </div> <!-- form-group// -->
     <!-- form-group// -->       
    <input type="hidden" value="1" id="status" name ="status" />
    <input type="hidden" value="2" id="roleId" name ="roleId"/>
    <input type="hidden" id="message" name="message" />        
    <div class="form-group">
        <button id="btnRegister" class="btn btn-primary btn-block"><i class="fa fa-paper-plane" aria-hidden="true"></i>  Đăng Ký  </button>
   	</div>                                                               
</form>
     
      <!-- form-group// -->      
    <p class="text-center">Have an account? <a href="<c:url value ='/dang-nhap'/>">Log In</a> </p>  
</article>
</div> <!-- card.// -->

<!--container end.//-->
<script>

function trim(str)
{
	return str.replace(/^\s+|\s+$/,'');
}

$('#btnRegister').click(function(){
if($('#passwordConfirm').val() !== $('#password').val())
	{
		Swal.fire({
			  icon: 'error',
			  title: 'Lỗi',
			  text: 'Mật khẩu xác nhận không đúng !'
		})
		$('#passwordConfirm').focus();
	} 
	else if(trim($('#passwordConfirm').val()).length < 1 || trim($('#password').val()).length < 1 && trim($('#userName').val()).length < 1 || trim($('#fullName').val()).length < 1 || trim($('#email').val()).length < 1 || trim($('#phonenumber').val()).length < 1 )
	{
			Swal.fire({
				  icon: 'error',
				  title: 'Lỗi',
				  text: 'Bạn chưa nhập đủ thông tin đăng ký !'
				})
	}
		else
		{
			$("#registerForm").submit();
		}
});
	
	/* $('#btnRegister').click(function(){
			var result = " ";
				for(var i = 0 ; i < $(".form-control").length ; i ++ ){
						if(trim($(".form-control")[i].val()).length < 1)
							{
								Swal.fire({
									  icon: 'error',
									  title: 'Lỗi',
									  text: 'Bạn chưa nhập đủ thông tin' 
									})
							}else if($('#passwordConfirm').val() !== $('#password').val())
							{
								Swal.fire({
									  icon: 'error',
									  title: 'Lỗi',
									  text: 'Mật khẩu xác nhận không đúng !'
									})
							}
							
					}
				
		}
	}); */
</script>
</body>
</html>