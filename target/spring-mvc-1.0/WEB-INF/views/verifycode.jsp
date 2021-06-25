<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Verified code</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="main-verification-input-wrap">
        <c:if test="${not empty message}">
         <div id="alert" class="alert alert-${alert} alert-dismissible">${message}
			<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>
		</c:if>
            <ul>
                <li>Hệ thống đã gửi email xác nhận cho bạn . Hãy nhập mã xác nhận vào ô bên dưới để xác minh</li>
            </ul>
            <div class="main-verification-input fl-wrap">
             <form id="formAuthCode" action="<c:url value ='/verify-code'/>" method="post">
                <div class="main-verification-input-item"><input type="text" id="authcode" name="authcode" placeholder="Nhập mã xác nhận"> </div>
            </form>
            <button id="btnSubmit"class="main-verification-button">Xác minh</button>
            </div>
        </div>
    </div>
</div>
<script>
	function trim(str){
		return str.replace(/^\s+|\s+$/,'');
	}	
	
	$('#btnSubmit').click(function(){
	if($('#authcode').val() === '' || trim($('#authcode').val()).length < 1){
				Swal.fire({
				icon: 'error',
				 title: 'Lỗi',
				 text: 'Bạn chưa nhập mã xác minh !'
				})
	}else 
		{
			$('#formAuthCode').submit();
		}
	});
</script>
</body>
</html>