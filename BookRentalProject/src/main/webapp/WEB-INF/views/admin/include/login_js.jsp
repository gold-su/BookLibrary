<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function loginForm() {
		console.log('loginForm() CALLED!!');
		
		let form = document.login_form;
		
		if (form.a_m_id.value == '') {
			alert('INPUT ADMIN ID.');
			form.a_m_id.focus();
			
		} else if (form.a_m_pw.value == '') {
			alert('INPUT ADMIN PW.');
			form.a_m_pw.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>