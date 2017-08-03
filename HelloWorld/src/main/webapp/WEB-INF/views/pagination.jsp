
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
	var ctxPath = document.getElementById("ctxPath").value;
	var pageNumber = $('#pageNum').val();
	var numOfPages = $('#numOfPages').val();
	if(pageNumber - 5  < 1){
		$('#skipPrev').attr('disabled', 'disabled');
		$('#skipPrev').attr('src',ctxPath + '/images/button_skip_previous_disabled.png');
	}else{
		$('#skipPrev').removeAttr('disabled');
		$('#skipPrev').attr('src',ctxPath + '/images/button_skip_previous.png');
	}
	if(pageNumber <= 1){
		$('#prev').attr('disabled', 'disabled');
		$('#prev').attr('src',ctxPath + '/images/button_previous_disabled.png');
	}else{
		$('#prev').removeAttr('disabled');
		$('#prev').attr('src',ctxPath + '/images/button_previous.png');
	}
	if(pageNumber >= numOfPages ){
		$('#next').attr('disabled', 'disabled');
		$('#next').attr('src',ctxPath + '/images/button_next_disabled.png');
	}else{
		$('#next').removeAttr('disabled');
		$('#next').attr('src',ctxPath + '/images/button_next.png');
	}
	if(pageNumber +5 > numOfPages){
		$('#skipNext').attr('disabled', 'disabled');
		$('#skipNext').attr('src',ctxPath + '/images/button_skip_next_disabled.png');
	}else{
		$('#skipNext').removeAttr('disabled');
		$('#skipNext').attr('src',ctxPath + '/images/button_skip_next.png');
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
		var classname;
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
				td = '<td class = "gallery-cell" style = "height:150px; width:150px;" > <div class="responsive"><div class="img"><img src=img/' + imageNames[i] + ' height="200" width ="200" />' +imageNames[i] +' </div></div> </td>';
				newHTML = newHTML+td;
				
			}
			newHTML = newHTML + '</tbody>';
			$('#mainTable').html(newHTML);
		}
		
		classname = document.getElementsByClassName("img");
		Array.from(classname).forEach(function(element) {
			element.addEventListener('click', opendialog);
		});
	}
	function changePageNumber(pageNumber){
		var ctxPath = document.getElementById("ctxPath").value;
		var pageLabel;
		var numOfPages;
		if($('#pageLabel').length >0 && pageNumber){
			numOfPages = $('#numOfPages').val();
			
			pageLabel  = $('#pageLabel');
			pageLabel.html('<label id = "pageLabel" >Page ' + pageNumber + ' of ' + numOfPages + '</label>');
			if(pageNumber - 5  < 1){
				$('#skipPrev').attr('disabled', 'disabled');
				$('#skipPrev').attr('src',ctxPath + '/images/button_skip_previous_disabled.png');
			}else{
				$('#skipPrev').removeAttr('disabled');
				$('#skipPrev').attr('src',ctxPath + '/images/button_skip_previous.png');
			}
			if(pageNumber <= 1){
				$('#prev').attr('disabled', 'disabled');
				$('#prev').attr('src',ctxPath + '/images/button_previous_disabled.png');
			}else{
				$('#prev').removeAttr('disabled');
				$('#prev').attr('src',ctxPath + '/images/button_previous.png');
			}
			if(pageNumber >= numOfPages ){
				$('#next').attr('disabled', 'disabled');
				$('#next').attr('src',ctxPath + '/images/button_next_disabled.png');
			}else{
				$('#next').removeAttr('disabled');
				$('#next').attr('src',ctxPath + '/images/button_next.png');
			}
			if(pageNumber +5 > numOfPages){
				$('#skipNext').attr('disabled', 'disabled');
				$('#skipNext').attr('src',ctxPath + '/images/button_skip_next_disabled.png');
			}else{
				$('#skipNext').removeAttr('disabled');
				$('#skipNext').attr('src',ctxPath + '/images/button_skip_next.png');
			}
		}
	}
</script>
<body>
<input type="hidden" id = "pageNum" value = '<%=pageNum%>' /> 
<input type ="hidden" id = "numOfPages" value =  '<%=maxPages%>' />
<input type = "hidden" id = "ctxPath" value = "${contextPath}" />

	<div id="pagination">
	<%if(pageNum > 5 ){%>
		<input id = "skipPrev"   type="image" src="${contextPath}/images/button_skip_previous.png" onclick="next(-5);"/>
	<%}else{ %>
		<input id = "skipPrev"   type="image" src="${contextPath}/images/button_skip_previous_disabled.png" disabled="disabled" onclick="next(-5);" />
	<%}%>	
	<%if(pageNum > 1 ){%>
		<input id = "prev"  type="image" src="${contextPath}/images/button_previous.png" onclick="next(-1);"/>
	<%}else{ %>
		<input id = "prev"  type="image" src="${contextPath}/images/button_previous_disabled.png" disabled="disabled" onclick="next(-1);"/>
	<%}%>	
	
	<label id = "pageLabel" >Page <%=pageNum%> of <%=maxPages%></label>
	
	<%if(pageNum < maxPages ){%>
	<input id = "next" type="image" src="${contextPath}/images/button_next.png" onclick="next(1);"/>
	<%}else{ %>
		<input id = "next" type="image" src="${contextPath}/images/button_next_disabled.png" disabled="disabled" onclick="next(1);"/>
	<%}%>	
	
	<%if(pageNum < maxPages ){%>
		<input id = "skipNext"  type="image" src="${contextPath}/images/button_skip_next.png" onclick="next(5);"/>
	<%}else{ %>
			<input id = "skipNext"  type="image" src="${contextPath}/images/button_skip_next_disabled.png" disabled="disabled" onclick="next(5);" />
	<%}%>	
	<!-- 	<button id = "skipPrev" onclick="next(-5);" >Skip Previous</button>
		<button id = "prev" onclick="next(-1);" >Previous</button>
		
		<button id = "next" onclick="next(1);" >Next</button>
		<button id = "skipNext" onclick="next(5);" >Skip Next</button> -->

	</div>
</body>
