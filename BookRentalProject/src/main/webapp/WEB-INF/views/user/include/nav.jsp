<%@page import="com.office.library.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/user/include/nav.css' />" rel="stylesheet" type="text/css">

<jsp:include page="./nav_js.jsp" />

<nav>
	
	<div id="nav_wrap">
		
		<%
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		if (loginedUserMemberVo != null) {
		%>
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/user/member/logoutConfirm' />">로그아웃</a></li>
				<li><a href="<c:url value='/user/member/modifyAccountForm' />">계정수정</a></li>
				<li><a href="<c:url value='/book/user/enterBookshelf' />">나의책장</a></li>
			</ul>
		</div>
		<%
		} else {
		%>
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/user/member/loginForm' />">로그인</a></li>
				<li><a href="<c:url value='/user/member/createAccountForm' />">회원가입</a></li>
			</ul>
		</div>
		<%
		}
		%>
		
		<div class="search">
			
			<form action="<c:url value='/book/user/searchBookConfirm' />" name="search_book_form" method="get">
				<input type="text" name="b_name" placeholder="Enter the name of the book you are looking for.">
				<input type="button" value="search" onclick="searchBookForm();">
			</form>
			
		</div>
		
	</div>
	
</nav>