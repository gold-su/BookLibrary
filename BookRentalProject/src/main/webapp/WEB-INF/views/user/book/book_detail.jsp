<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/book_detail.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>BOOK DETAIL</h3>
				
			</div>
			
			<div class="book_detail">
			
				<ul>
					<li>
						<img src="<c:url value="/libraryUploadImg/${bookVo.b_thumbnail}"/>">
					</li>
					<li>
						<table>
							<tr>
								<td>도서명</td>
								<td>${bookVo.b_name}</td>
							</tr>
							<tr>
								<td>저자</td>
								<td>${bookVo.b_author}</td>
							</tr>
							<tr>
								<td>발행처</td>
								<td>${bookVo.b_publisher}</td>
							</tr>
							<tr>
								<td>발행년도</td>
								<td>${bookVo.b_publish_year}</td>
							</tr>
							<tr>
								<td>ISBN</td>
								<td>${bookVo.b_isbn}</td>
							</tr>
							<tr>
								<td>청구기호</td>
								<td>${bookVo.b_call_number}</td>
							</tr>
							<tr>
								<td>대출가능</td>
								<td>
								<c:choose>
									<c:when test="${bookVo.b_rantal_able eq '0'}"> <c:out value="X" /> </c:when>
									<c:when test="${bookVo.b_rantal_able eq '1'}"> <c:out value="O" /> </c:when>
									<c:otherwise> <c:out value="X" /> </c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<td>등록일</td>
								<td>${bookVo.b_reg_date}</td>
							</tr>
							<tr>
								<td>수정일</td>
								<td>${bookVo.b_mod_date}</td>
							</tr>
						</table>
					</li>
				</ul>
				
			</div>
		
			<div class="buttons">
				
				<c:choose>
					<c:when test="${bookVo.b_rantal_able eq 0}">
						<a href="#none">대출중</a>
					</c:when>
					<c:when test="${bookVo.b_rantal_able eq 1}">
						<c:url value='/book/user/rentalBookConfirm' var='rental_url'>
							<c:param name='b_no' value='${bookVo.b_no}'/>
						</c:url>
						<a class="rental_book_button" href="${rental_url}">도서 대출</a>
					</c:when>
				</c:choose>
				
			</div>
			
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />

</body>
</html>