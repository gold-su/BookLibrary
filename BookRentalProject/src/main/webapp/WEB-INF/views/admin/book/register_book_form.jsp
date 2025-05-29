<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/register_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/register_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>REGISTER BOOK FORM</h3>
				
			</div>
		
			<div class="register_book_form">
			
				<form action="<c:url value='/book/admin/registerBookConfirm' />" name="register_book_form" method="post" enctype="multipart/form-data">
					
					<input type="text" name="b_name" placeholder="INPUT BOOK NAME."> <br>
					<input type="text" name="b_author" placeholder="INPUT BOOK AUTHOR."> <br>
					<input type="text" name="b_publisher" placeholder="INPUT BOOK PUBLISHER."> <br>
					<input type="text" name="b_publish_year" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
					<input type="text" name="b_isbn" placeholder="INPUT BOOK ISBN."> <br>
					<input type="text" name="b_call_number" placeholder="INPUT BOOK CALL NUMBER."> <br>
					<select name="b_rantal_able">
						<option value="">SELECT BOOK RANTAL ABLE.</option>
						<option value="0">UNABLE.</option>
						<option value="1">ABLE.</option>
					</select><br>
					<input type="file" name="file"><br>
					<input type="button" value="register book" onclick="registerBookForm();"> 
					<input type="reset"	value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>