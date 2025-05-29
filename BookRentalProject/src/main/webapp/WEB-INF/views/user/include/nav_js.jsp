<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function searchBookForm() {
		console.log('searchBookForm() CALLED!!');
		
		let form = document.search_book_form;
		
		if (form.b_name.value == '') {
			alert('Enter the name of the book you are looking for.');
			form.b_name.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>