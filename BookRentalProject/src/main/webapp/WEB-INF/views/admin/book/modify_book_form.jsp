<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/modify_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/modify_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>MODIFY BOOK FORM</h3>
				
			</div>
		
			<div class="modify_book_form">
			
				<form action="<c:url value='/book/admin/modifyBookConfirm' />" name="modify_book_form" method="post" enctype="multipart/form-data">
					
					<input type="hidden" name="b_no" value="${bookVo.b_no}">
					
					<input type="text" name="b_name" value="${bookVo.b_name}" placeholder="INPUT BOOK NAME."> <br>
					<input type="text" name="b_author" value="${bookVo.b_author}" placeholder="INPUT BOOK AUTHOR."> <br>
					<input type="text" name="b_publisher" value="${bookVo.b_publisher}" placeholder="INPUT BOOK PUBLISHER."> <br>
					<input type="text" name="b_publish_year" value="${bookVo.b_publish_year}" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
					<input type="text" name="b_isbn" value="${bookVo.b_isbn}" placeholder="INPUT BOOK ISBN."> <br>
					<input type="text" name="b_call_number" value="${bookVo.b_call_number}" placeholder="INPUT BOOK CALL NUMBER."> <br>
					<select name="b_rantal_able">
						<option value="">SELECT BOOK RANTAL ABLE.</option>
						<option value="0" <c:if test="${bookVo.b_rantal_able eq '0'}"> selected </c:if>>UNABLE.</option>
						<option value="1" <c:if test="${bookVo.b_rantal_able eq '1'}"> selected </c:if>>ABLE.</option>
					</select><br>
					<input type="file" name="file"><br>
					<input type="button" value="modify book" onclick="modifyBookForm();"> 
					<input type="reset" value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>