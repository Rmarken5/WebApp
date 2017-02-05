<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	int pageNum = 0;
	long maxPages = 0;
	if (null != request.getAttribute("pageNum") && (Integer) request.getAttribute("pageNum") >= 0) {
		pageNum = (Integer) request.getAttribute("pageNum");
	} else {
		pageNum = 1;
	}
	if (null != request.getAttribute("maxPages") && (Long) request.getAttribute("maxPages") >= 0) {
		maxPages = (Long) request.getAttribute("maxPages");
	} else {
		maxPages = 1;
	}
%>
<script>
$(document).ready(function(){
	var pageNumber = $('#pageNum').val();
	var numOfPages = $('#numOfPages').val();
	if(pageNumber - 5  < 1){
		$('#skipPrev').prop('disabled', true);
	}else{
		$('#skipPrev').prop('disabled', false);
	}
	if(pageNumber <= 1){
		$('#prev').prop('disabled', true);
	}else{
		$('#prev').prop('disabled', false);
	}
	if(pageNumber >= numOfPages ){
		$('#next').prop('disabled', true);
	}else{
		$('#next').prop('disabled', false);
	}
	if(pageNumber +5 > numOfPages){
		$('#skipNext').prop('disabled', true);
	}else{
		$('#skipNext').prop('disabled', false);
	}
});
function next(increment){
	var currentPage = $("#pageNum").val();
	var currentPageVar = $("#pageNum");
	var nextPage;
	var imageNames;	
	var newHTML;
	var td;
	var tr;
		if (currentPage && !isNaN(currentPage)) {
			nextPage = parseInt(currentPage) + increment;
			$.ajax({
				type : 'GET',
				url : '../gallery/json/' + nextPage,
				contentType: "application/text",
				dataType: "text",
				success : function(msg){
					generateNewTable(msg);
					$("#pageNum").val(nextPage);
					changePageNumber(nextPage);
				},
				error:function(exception){alert('Exeption:'+exception);}
			});
		}
	}
	
	function generateNewTable(msg){
		var imageNames;	
		var newHTML = '';
		var td;
		var tr;
		if(msg && "" != msg && '' != msg){
			imageNames = msg.split(',');
			$('#mainTable').html('');
			newHTML = '<tbody>';
			for(var i = 0; i < imageNames.length; i++ ){
				if(i != 0 && i % 5 == 0){
					tr = '</tr>';
					newHTML = newHTML+tr;
				}
				if(i % 5 == 0){
					tr = '<tr>';
					newHTML = newHTML+tr;
				}
				td = '<td> <div class="responsive"><div class="img"><img src=img/' + imageNames[i] + ' />' +imageNames[i] +' </div></div> </td>';
				newHTML = newHTML+td;
				
			}
			newHTML = newHTML + '</tbody>';
			$('#mainTable').html(newHTML);
		}
	}
	function changePageNumber(pageNumber){
		var pageLabel;
		var numOfPages;
		if($('#pageLabel').length >0 && pageNumber){
			numOfPages = $('#numOfPages').val();
			
			pageLabel  = $('#pageLabel');
			pageLabel.html('<label id = "pageLabel" >Page ' + pageNumber + ' of ' + numOfPages + '</label>');
			if(pageNumber - 5  < 1){
				$('#skipPrev').prop('disabled', true);
			}else{
				$('#skipPrev').prop('disabled', false);
			}
			if(pageNumber <= 1){
				$('#prev').prop('disabled', true);
			}else{
				$('#prev').prop('disabled', false);
			}
			if(pageNumber >= numOfPages ){
				$('#next').prop('disabled', true);
			}else{
				$('#next').prop('disabled', false);
			}
			if(pageNumber +5 > numOfPages){
				$('#skipNext').prop('disabled', true);
			}else{
				$('#skipNext').prop('disabled', false);
			}
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<input type="hidden" id = "pageNum" value = '<%=pageNum%>' /> 
<input type ="hidden" id = "numOfPages" value =  '<%=maxPages%>' />

	<div id="pagination">
		<button id = "skipPrev" onclick="next(-5);" >Skip Previous</button>
		<button id = "prev" onclick="next(-1);" >Previous</button>
		<label id = "pageLabel" >Page <%=pageNum%> of <%=maxPages%></label>
		<button id = "next" onclick="next(1);" >Next</button>
		<button id = "skipNext" onclick="next(5);" >Skip Next</button>

	</div>
</body>
</html>