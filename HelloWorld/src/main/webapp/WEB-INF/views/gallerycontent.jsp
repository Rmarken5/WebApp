<%
	int i = 0;
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/galleryStyle.css">
<script type="text/javascript">


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
	    close: function () { $(this).dialog('close'); }
	  });
	  $dialog.dialog('open');
	} 
	
	$(document).ready(function() {
	/* 	$("#mainTable").addClass("galleryImageTable");
		//$("#mainTable tr").addClass("galleryImageRow");
		$("#mainTable td").addClass("galleryImageCell"); */
		
		// $(document).css('background-image', 'url( /HelloWorld/src/main/webapp/WEB-INF/resources/images/i89.jpg)');

		var classname = document.getElementsByClassName("img");
		
		

		Array.from(classname).forEach(function(element) {
			element.addEventListener('click', opendialog);
		});
	});
</script>
<title>Valley Jam - Gallery Page</title>


	<form:form id="mainForm">
	<div id="dialog1" title="Dialog Title" hidden="hidden">I'm a dialog</div>
	<div class = "gallery-container">
		<table id="mainTable" style="margin:auto;">
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
			<tr >
				<%
					}
				%>
				<td class = "gallery-cell" style = "height:150px; width:150px;">
					<div class="responsive" > 
						<div class="img" >
							<img src="img/${image}" height="200" width ="200" alt="Image -  ${image}" />${image}
						</div>
					</div>
				</td>


				<%
					i++;
				%>
				</c:forEach>
		</table>
		</div>
		
	</form:form>
	<%@include file="pagination.jsp"%>