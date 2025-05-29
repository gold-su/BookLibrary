<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function createAccountForm() {
		console.log('createAccountForm() CALLED!!');
		
		let form = document.create_account_form;
		
		if (form.a_m_id.value == '') {
			alert('INPUT ADMIN ID.');
			form.a_m_id.focus();
			
		} else if (form.a_m_pw.value == '') {
			alert('INPUT ADMIN PW.');
			form.a_m_pw.focus();
			
		} else if (form.a_m_pw_again.value == '') {
			alert('INPUT ADMIN PW AGAIN.');
			form.a_m_pw_again.focus();
			
		} else if (form.a_m_pw.value != form.a_m_pw_again.value) {
			alert('Please check your password again.');
			form.a_m_pw.focus();
			
		} else if (form.a_m_name.value == '') {
			alert('INPUT ADMIN NAME.');
			form.a_m_name.focus();
			
		} else if (form.a_m_gender.value == '') {
			alert('SELECET ADMIN GENDER.');
			form.a_m_gender.focus();
			
		} else if (form.a_m_part.value == '') {
			alert('INPUT ADMIN PART.');
			form.a_m_part.focus();
			
		} else if (form.a_m_position.value == '') {
			alert('INPUT ADMIN POSITION.');
			form.a_m_position.focus();
			
		} else if (form.a_m_mail.value == '') {
			alert('INPUT ADMIN MAIL.');
			form.a_m_mail.focus();
			
		} else if (form.a_m_phone.value == '') {
			alert('INPUT ADMIN PHONE.');
			form.a_m_phone.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>