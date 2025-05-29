<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function modifyAccountForm() {
		console.log('modifyAccountForm() CALLED!!');
		
		let form = document.modify_account_form;
		
		if (form.u_m_name.value == '') {
			alert('INPUT USER NAME.');
			form.u_m_name.focus();
			
		} else if (form.u_m_gender.value == '') {
			alert('SELECET USER GENDER.');
			form.u_m_gender.focus();
			
		} else if (form.u_m_mail.value == '') {
			alert('INPUT USER MAIL.');
			form.u_m_mail.focus();
			
		} else if (form.u_m_phone.value == '') {
			alert('INPUT USER PHONE.');
			form.u_m_phone.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>