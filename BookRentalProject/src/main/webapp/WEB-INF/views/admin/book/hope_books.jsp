<%@page import="java.util.List"%>
<%@page import="com.office.library.book.BookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/hope_books.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>HOPE BOOKS</h3>
				
			</div>
			
			<div class="book_list">
			
				<table>
					<thead>
						<tr>
							<th>요청회원</th>
							<th>도서명</th>
							<th>저자</th>
							<th>발행처</th>
							<th>발행연도</th>
							<th>입고</th>
						</tr>
					</thead>
					
					<tbody>
						
						<c:forEach var="item" items="${hopeBookVos}">
							<tr>
								<td>${item.u_m_id}</td>
								<td>${item.hb_name}</td>
								<td>${item.hb_author}</td>
								<td>${item.hb_publisher}</td>
								<td>${item.hb_publish_year}</td>
								<td>
									<c:choose>
										<c:when test="${item.hb_result eq 0}">
											<c:url value="/book/admin/registerHopeBookForm" var="hope_book_url">
												<c:param name="hb_no" value="${item.hb_no}"/>
												<c:param name="hb_name" value="${item.hb_name}"/>
												<c:param name="hb_author" value="${item.hb_author}"/>
												<c:param name="hb_publisher" value="${item.hb_publisher}"/>
												<c:param name="hb_publish_year" value="${item.hb_publish_year}"/>
											</c:url>
											<a href="${hope_book_url}">입고</a>
										</c:when>
										<c:when test="${item.hb_result eq 1}">
											완료
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
					
				</table>
				
			</div>
			
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>