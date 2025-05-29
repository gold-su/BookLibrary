<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function findPasswordForm() {
		console.log('findPasswordForm() CALLED!!');
		
		let form = document.find_password_form;
		
		if (form.u_m_id.value == '') {
			alert('INPUT USER ID.');
			form.u_m_id.focus();
			
		} else if (form.u_m_name.value == '') {
			alert('INPUT USER NAME.');
			form.u_m_name.focus();
			
		} else if (form.u_m_mail.value == '') {
			alert('INPUT USER MAIL.');
			form.u_m_mail.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>