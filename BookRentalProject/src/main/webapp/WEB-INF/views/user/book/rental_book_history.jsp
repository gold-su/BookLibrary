<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/rental_book_history.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>RENTAL BOOK HISTORY</h3>
				
			</div>
			
			<div class="rental_book_history">
				
				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>ISBN</th>
							<th>청구기호</th>
							<th>대출일</th>
							<th>반납일</th>
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
								<td>
								<c:choose>
									<c:when test="${fn:contains(item.rb_end_date, '1000-01-01')}">대출중</c:when>
									<c:otherwise>${item.rb_end_date}</c:otherwise>
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