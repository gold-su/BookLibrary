<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/user/home.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="./include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="banners">
				<div class="large_banner">
					<a href="#none"><img src="<c:url value='/resources/img/user/large_banner.jpg' />"></a>
				</div>
				<div class="small_banners">
					<ul>
						<li><a href="#none"><img src="<c:url value='/resources/img/user/small_banner1.png' />"></a></li>
						<li><a href="#none"><img src="<c:url value='/resources/img/user/small_banner2.png' />"></a></li>
						<li><a href="#none"><img src="<c:url value='/resources/img/user/small_banner3.png' />"></a></li>
						<li><a href="#none"><img src="<c:url value='/resources/img/user/small_banner4.png' />"></a></li>
					</ul>
				</div>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../include/footer.jsp" />

</body>
</html>