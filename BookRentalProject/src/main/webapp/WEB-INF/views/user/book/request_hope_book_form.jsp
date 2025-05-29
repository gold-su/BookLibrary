<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/request_hope_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/request_hope_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>REQUEST HOPE BOOK FORM</h3>
				
			</div>
		
			<div class="request_hope_book_form">
			
				<form action="<c:url value='/book/user/requestHopeBookConfirm' />" name="request_hope_book_form" method="get">
					
					<input type="text"		name="hb_name" 			placeholder="* INPUT HOPE BOOK NAME."> <br>
					<input type="text"		name="hb_author" 		placeholder="* INPUT HOPE BOOK AUTHOR."> <br>
					<input type="text"		name="hb_publisher"		placeholder="INPUT HOPE BOOK PUBLISHER."> <br>
					<input type="text"		name="hb_publish_year" 	placeholder="INPUT HOPE BOOK PUBLISH YEAR."> <br>
					<input type="button"	value="request hope book" onclick="requestHopeBookForm();"> 
					<input type="reset"		value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>