<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/create_account_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/create_account_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>CREATE ACCOUNT FORM</h3>
				
			</div>
		
			<div class="create_account_form">
			
				<form action="<c:url value='/admin/member/createAccountConfirm' />" name="create_account_form" method="post">
					
					<input type="text" name="a_m_id" placeholder="INPUT ADMIN ID."> <br>
					<input type="password" name="a_m_pw" placeholder="INPUT ADMIN PW."> <br>
					<input type="password" name="a_m_pw_again" placeholder="INPUT ADMIN PW AGAIN."> <br>
					<input type="text" name="a_m_name" placeholder="INPUT ADMIN NAME."> <br>
					<select name="a_m_gender">
						<option value="">SELECET ADMIN GENDER.</option>
						<option value="M">Man</option>
						<option value="W">Woman</option>
					</select> <br>
					<input type="text" name="a_m_part" placeholder="INPUT ADMIN PART."> <br>
					<input type="text" name="a_m_position" placeholder="INPUT ADMIN POSITION."> <br>
					<input type="email" name="a_m_mail" placeholder="INPUT ADMIN MAIL." ><br>
					<input type="text" name="a_m_phone" placeholder="INPUT ADMIN PHONE."> <br>
					<input type="button" value="create account" onclick="createAccountForm();"> 
					<input type="reset" value="reset">
					
				</form>
				
			</div>
			
			<div class="login">
				
				<a href="<c:url value='/admin/member/loginForm' />">login</a>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>