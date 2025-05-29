<%@page import="com.office.library.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/modify_account_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/modify_account_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>MODIFY ACCOUNT FORM</h3>
				
			</div>
		
			<div class="modify_account_form">
			
				<form action="<c:url value='/user/member/modifyAccountConfirm' />" name="modify_account_form" method="post">
					
					<input type="hidden" name="u_m_no" value="${loginedUserMemberVo.u_m_no}">
					
					<input type="text" name="u_m_id" value="${loginedUserMemberVo.u_m_id}" readonly disabled> <br>
					<input type="password" name="u_m_pw" value="******" readonly disabled> <br>
					<input type="text" name="u_m_name" value="${loginedUserMemberVo.u_m_name}" placeholder="INPUT USER NAME."> <br>
					<select name="u_m_gender">
						<option value="">SELECET USER GENDER.</option>
						<option value="M" <c:if test="${loginedUserMemberVo.u_m_gender eq 'M'}"> selected </c:if>>Man</option>
						<option value="W" <c:if test="${loginedUserMemberVo.u_m_gender eq 'W'}"> selected </c:if>>Woman</option>
						
					</select> <br>
					<input type="email"	name="u_m_mail" value="${loginedUserMemberVo.u_m_mail}" placeholder="INPUT USER MAIL." ><br>
					<input type="text" name="u_m_phone"	value="${loginedUserMemberVo.u_m_phone}" placeholder="INPUT USER PHONE."> <br>
					<input type="button" value="modify account" onclick="modifyAccountForm();"> 
					<input type="reset" value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>