<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="com.thienday.util.SecurityUtils"%>
<article class="bg-secondary mb-3">
	<div class="card-body text-center">
		<h3 class="text-white mt-3">THIÊN ĐÂY NEWS</h3>
		<p class="h5 text-white">
			TRANG TIN TỨC <br> CẬP NHẬT TIN TRONG NƯỚC VÀ NGOÀI NƯỚC MỖI
			NGÀY
		</p>
		<br>
		<p>
			<a class="btn btn-warning" href="<c:url value ='/trang-chu'/>">
				Trở về trang chủ </a>
			<security:authorize access="isAnonymous()">
				<a class="btn btn-warning" href="<c:url value ='/dang-nhap'/>">
					Đăng nhập </a>
			</security:authorize>
		</p>
	</div>
</article>
<div style="width: 100%">
	<iframe width="100%" height="600" frameborder="0" scrolling="no"
		marginheight="0" marginwidth="0"
		src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=493/17%20L%C3%AA%20%C4%90%E1%BB%A9c%20Th%E1%BB%8D%20P.16%20Qu%E1%BA%ADn%20G%C3%B2%20V%E1%BA%A5p.+(Home)&amp;t=&amp;z=21&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"></iframe>
</div>
<!-- /.container -->
