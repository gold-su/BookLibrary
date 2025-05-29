<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav>
    <div class="login_form">
        <form action="<c:url value='/admin/member/loginConfirm' />" name="login_form" method="post">
            <input type="text" name="a_m_id" placeholder="INPUT ADMIN ID."> <br>
            <input type="password" name="a_m_pw" placeholder="INPUT ADMIN PW."> <br>
            <input type="button" value="login" onclick="loginForm();">
            <input type="reset" value="reset">    
        </form>
    </div>
</nav>

<script>
function loginForm() {
    document.login_form.submit();
}
</script>
