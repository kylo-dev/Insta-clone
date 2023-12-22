// 수정 좋아요 카운트 증가
async function onFeedLoad(imageId){
	let msg = await like(imageId);
	console.log(msg);
	let likeCount = $("#photo_likes_count_"+imageId).text();
	if(msg.data === 1){
		$("#photo_likes_count_"+imageId).text(Number(likeCount)+1);
		$("#"+imageId).toggleClass("heart-clicked fa-heart fa-heart-o");
		// toggleClass() -> 해당 class가 있으면 삭제하고, 없는 경우 추가
	}else if(msg.data === 0){
		$("#photo_likes_count_"+imageId).text(Number(likeCount)-1);
		$("#"+imageId).toggleClass("heart-clicked fa-heart fa-heart-o");	
	}else{
		alert(msg);
	}
}

async function like(imageId){
	let response = await fetch(`/image/like/${imageId}`, {
		method: "POST"
	});
	return await response.json();
}