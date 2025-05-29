<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/list_hope_book.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>HOPE BOOKS</h3>
				
			</div>
			
			<div class="list_hope_books">
				
				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>저자</th>
							<th>발행처</th>
							<th>발행연도</th>
							<th>요청일</th>
							<th>요청 수정일</th>
							<th>처리상태</th>
							<th>처리상태 수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${hopeBookVos}">
							<tr>
							
								<td>${item.hb_name}</td>
								<td>${item.hb_author}</td>
								<td>${item.hb_publisher}</td>
								<td>${item.hb_publish_year}</td>
								<td>${item.hb_reg_date}</td>
								<td>${item.hb_mod_date}</td>
								<td>
								<c:choose>
									<c:when test="${item.hb_result eq 0}"> <c:out value="검토중" /> </c:when>
									<c:when test="${item.hb_result eq 1}"> <c:out value="완료" /> </c:when>
									<c:otherwise> <c:out value="입고대기" /> </c:otherwise>
								</c:choose>
								</td>
								<td>${item.hb_result_last_date}</td>
							
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