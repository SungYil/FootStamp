/*$(function(){
	$('#searchKeyword').keydown(function(){
		var url_location = $('#search').attr('action');
		console.log(url_location);
		var keyword = $('#searchKeyword').val();
		console.log(keyword);
		$.ajax({
			url : url_location,
			type : "get",
			data : {
				"keyword" : keyword
			},
			success : function(response) {
				var data = JSON.parse(response);
				console.log(data);
				if (data.result.trim() == "success") {
					alert("success");
				} else {
					alert(data.result)
				}
			},
			error : function() {
				alert("keyword error!")
			}
		});
	});
});*/