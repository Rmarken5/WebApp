<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="banner.jsp"%>
<%
	int i = 0;
%>
<style type="text/css">
div.img {
	border: 1px solid #ccc;
}

div.img:hover {
	border: 1px solid #777;
}

div.img img {
	width: 100%;
	height: auto;
}

div.desc {
	padding: 15px;
	text-align: center;
}

* {
	box-sizing: border-box;
}

.responsive {
	padding: 0 6px;
	float: left;
	/*width: 24.99999%;*/
}

</style>
<script type="text/javascript">

	$(document).ready(function() {
		$("#mainTable").addClass("galleryImageTable");
		//$("#mainTable tr").addClass("galleryImageRow");
		$("#mainTable td").addClass("galleryImageCell");
		$('body').css('background', '#0642a3');
		// $(document).css('background-image', 'url( /HelloWorld/src/main/webapp/WEB-INF/resources/images/i89.jpg)');

		var classname = document.getElementsByClassName("img");
		
		
		function opendialog() {
			  var $dialog = $('#dialog1')
			  .html('<iframe style=" border-style: solid;border-color: #000000" src="' + this.children[0].src + '" width="100%" height="100%"></iframe>')
			  .dialog({
			    title: this.children[0].alt !== undefined ? this.children[0].alt  : 'Image',
			    autoOpen: false,
			   /*  dialogClass: 'dialog_fixed,ui-widget-header', */
			    modal: true,
			    height: this.children[0].naturalHeight,
			    width: this.children[0].naturalWidth,
			    draggable:true,
			    close: function () { $(this).remove(); }
			  });
			  $dialog.dialog('open');
			} 

		Array.from(classname).forEach(function(element) {
			element.addEventListener('click', opendialog);
		});
	});
</script>
<title>Valley Jam - Gallery Page</title>
</head>
<body>
	<form:form id="mainForm">
		<table id="mainTable">
			<tr>
				<c:forEach items="${galleryImages}" var="image">

					<%
						if (i % 5 == 0) {
					%>
				
			</tr>
			<%
				}
			%>
			<%
				if (i % 5 == 0) {
			%>
			<tr>
				<%
					}
				%>
				<td>
					<div class="responsive">
						<div class="img">
							<img src="img/${image}" alt="Image -  ${image}" />${image}
						</div>
					</div>
				</td>


				<%
					i++;
				%>
				</c:forEach>
		</table>
		<div id="dialog1" title="Dialog Title" hidden="hidden">I'm a dialog</div>
	</form:form>
	<%@include file="pagination.jsp"%>
</body>
</html>