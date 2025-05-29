<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function registerBookForm() {
		console.log('registerBookForm() CALLED!!');
		
		let form = document.register_book_form;
		
		if (form.b_name.value == '') {
			alert('INPUT BOOK NAME.');
			form.b_name.focus();
			
		} else if (form.b_author.value == '') {
			alert('INPUT BOOK AUTHOR.');
			form.b_author.focus();
			
		} else if (form.b_publisher.value == '') {
			alert('INPUT BOOK PUBLISHER.');
			form.b_publisher.focus();
			
		} else if (form.b_publish_year.value == '') {
			alert('INPUT BOOK PUBLISH YEAR.');
			form.b_publish_year.focus();
			
		} else if (form.b_isbn.value == '') {
			alert('INPUT BOOK ISBN.');
			form.b_isbn.focus();
			
		} else if (form.b_call_number.value == '') {
			alert('INPUT BOOK CALL NUMBER.');
			form.b_call_number.focus();
			
		} else if (form.b_rantal_able.value == '') {
			alert('SELECT BOOK RANTAL ABLE.');
			form.b_rantal_able.focus();
			
		} else if (form.file.value == '') {
			alert('SELECT IMAGE FILE');
			form.b_thumbnail.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>