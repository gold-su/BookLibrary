<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/bookshelf.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>MY BOOKSHELF</h3>
				
			</div>
			
			<%-- 대출 목록 --%>
			<div class="category_name">
				<h4>대출 목록</h4>
			</div>
			
			<div class="rental_book_list">
				
				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>ISBN</th>
							<th>청구기호</th>
							<th>대출일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${rentalBookVos}">
							<tr>
								<td>
								<c:url value='/book/user/bookDetail' var='detail_url'>
									<c:param name='b_no' value='${item.b_no}'/>
								</c:url>
								<a href="${detail_url}">${item.b_name}</a>
								</td>
								<td>${item.b_isbn}</td>
								<td>${item.b_call_number}</td>
								<td>${item.rb_start_date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			
			<%-- 
			 - 전체 대출 이력 
			 - 희망 도서 요청 
			 - 희망 도서 요청 목록 
			--%>
			<div class="other_category">
				<a href="<c:url value='/book/user/listupRentalBookHistory'/>">전체 대출 이력</a>
				<a href="<c:url value='/book/user/requestHopeBookForm'/>">희망 도서 요청</a>
				<a href="<c:url value='/book/user/listupRequestHopeBook'/>">희망 도서 요청 목록 </a>
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>