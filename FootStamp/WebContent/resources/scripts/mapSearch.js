/**
 * 지도검색결과화면에서
 */
window.onload=function(){
	var locationName = $('#locationName').html();
	console.log(locationName);

	// 서블릿에서 받아와야할 정보
	var array = [];
	var contentArray = [];

	//비동기 검색요청
	$.ajax({
		url : "/FootStamp/search.do",
		type : "get",
		data : {
			"keyword":locationName
		},
		success:function(res){
			var response=JSON.parse(res);
			var list=response.locationList;
			console.log(list);
			if (response.result.trim() == "success") {
				alert("검색 성공");
				//Search.java에서 받아온 list를 뿌려준다.
				for(var i=0;i<list.length;++i){
					array.push({
						lat : parseFloat(list[i].lat),
						lng : parseFloat(list[i].lng)
					});
					console.log(list[i].lat+" "+list[i].lng);
				}
				//Search.java에서 받아온 좋아요 개수등을 받아서 뿌려줘야한다.
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p> <a href="#">review 13</a></p> </div></div><div class="editDiv" align="right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>');
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p> <a href="#">review 13</a></p> </div></div><div class="editDiv" align="right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>');
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p> <a href="#">review 13</a></p> </div></div><div class="editDiv" align="right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>');

				initMap('registMap',false,array,contentArray);//스토리가 있을 경우
			} else {
				alert(data.result);
			}
			
		},
		error:function(){
			alert("등록실패");
		}
	});
}

