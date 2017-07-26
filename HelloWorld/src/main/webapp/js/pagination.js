/**
 * This file is intended to control the pagination for various screens.
 */

function next(){
	var nextPage = '<%=pageNum%>' + 1 ;
    $.ajax({
        type:'GET',
        url:'gallery/'+ nextPage,
        success: function (msg) {
            var data = $.parseJSON(msg);
            var img = data[0] == 0 ? '1.jpg' : '2.jpg';
            $("#durum").html('<img src="'+ img +'">');    
        }
    });
 }