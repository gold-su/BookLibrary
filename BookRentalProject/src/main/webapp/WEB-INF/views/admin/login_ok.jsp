<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav>
    <div class="section_wrap">
        <div class="word">
            <h3>LOGIN SUCCESS!!</h3>
        </div>
        
        <div class="others">
            <a href="<c:url value='/admin/member/logoutConfirm' />">logout</a>
            <a href="<c:url value='/admin/member/modifyAccountForm' />">modify account</a>
        </div>
    </div>
</nav>

<script>
function loginForm() {
    document.login_form.submit();
}
</script>
