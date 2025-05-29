<%@page import="com.office.library.admin.member.AdminMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/modify_account_form.css' />" rel="stylesheet" type="text/css">

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
			
				<form action="<c:url value='/admin/member/modifyAccountConfirm' />" name="modify_account_form" method="post">
					
					<input type="hidden" name="a_m_no" value="${loginedAdminMemberVo.a_m_no}">
					
					<input type="text" name="a_m_id" value="${loginedAdminMemberVo.a_m_id}" readonly disabled> <br>
					<input type="password" name="a_m_pw" value="******" readonly disabled> <br>
					<input type="text" name="a_m_name" value="${loginedAdminMemberVo.a_m_name}" placeholder="INPUT USER NAME."> <br>
					<select name="a_m_gender">
						<option value="">SELECET USER GENDER.</option>
						<c:set var="gender" value="${loginedAdminMemberVo.a_m_gender}" />
						<option value="M" <c:if test = "${gender eq 'M'}"> selected </c:if>>Man</option>
						<option value="W" <c:if test = "${gender eq 'W'}"> selected </c:if>>Woman</option>
					</select> <br>
					<input type="text" name="a_m_part" value="${loginedAdminMemberVo.a_m_part}" placeholder="INPUT USER PART." ><br>
					<input type="text" name="a_m_position" value="${loginedAdminMemberVo.a_m_position}" placeholder="INPUT USER POSITION." ><br>
					<input type="email"	name="a_m_mail"	value="${loginedAdminMemberVo.a_m_mail}" placeholder="INPUT USER MAIL." ><br>
					<input type="text"	name="a_m_phone" value="${loginedAdminMemberVo.a_m_phone}" placeholder="INPUT USER PHONE."> <br>
					<input type="button" value="modify account" onclick="modifyAccountForm();"> 
					<input type="reset"	value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>