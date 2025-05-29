<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/find_password_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/find_password_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
			
			<div class="word">
			
				<h3>FIND PASSWORD FORM</h3>
				<p>(We will send you a new password.)</p>
				
			</div>
			
			<div class="find_password_form">
			
				<form action="<c:url value='/admin/member/findPasswordConfirm' />" name="find_password_form" method="post">
					
					<input type="text" name="a_m_id" placeholder="INPUT ADMIN ID."> <br>
					<input type="text" name="a_m_name" placeholder="INPUT ADMIN NAME."> <br>
					<input type="text" name="a_m_mail" placeholder="INPUT ADMIN MAIL."> <br>
					<input type="button" value="find password" onclick="findPassword();"> 
					<input type="reset" value="reset">
					
				</form>
				
			</div>
			
			<div class="create_account_login">
				
				<a href="<c:url value='/admin/member/createAccountForm' />">create account</a>
				<a href="<c:url value='/admin/member/loginForm' />">login</a>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>