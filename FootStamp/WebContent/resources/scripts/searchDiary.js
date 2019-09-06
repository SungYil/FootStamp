/**
 * 등록화면에서
 */
window.onload=function(){
	// 서블릿에서 받아와야할 정보
	var array = [];
	var contentArray = [];
	
	array.push({
		lat : 37.5563989,
		lng : 126.9160863
	});
	array.push({
		lat : 37.5663989,
		lng : 126.9260863
	});
	contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p> <a href="#">review 13</a></p> </div></div><div class="editDiv" align="right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>');
	contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p> <a href="#">review 13</a></p> </div></div><div class="editDiv" align="right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>');
	

	
//	if()문 필요
	initMap('registMap',true, array,contentArray);//스토리가 있을 경우
//	initMap('map',true);// 만약 서버에서 나의 스토리 등록을 클릭시 해당 날짜의 스토리가 없을 경우 호출
	addSearchBar();//검색바 필요
	
}

