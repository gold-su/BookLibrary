<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav>
    <div class="section_wrap">
        <div class="word">
        	<h3>LOGIN FALL!!</h3>
        	<span>(만약 관리자 회원갑을 했다면, 최고 관리자(super admin)한테 승인 요청 후 로그인하세요.)</span>
        </div>
        
        <div class="others">
        	
        	<a href="<c:url value='/admin/member/createAccountFrom'/>">create account</a>
        	<a href="<c:url value='/admin/member/loginForm'/>">login</a>
     
        	
        </div>
    </div>
</nav>

<script>
function loginForm() {
    document.login_form.submit();
}
</script>