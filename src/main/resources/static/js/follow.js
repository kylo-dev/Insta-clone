function follow(check, userId, i) {
	let url = "/follow/" + userId;
	let method = check ? "POST" : "DELETE";

	$.ajax({
		url: url,
		method: method,
		success: function (data) {
			if (data.data === 1) {
				let follow_item_el = document.querySelector("#follow_item_" + i);
				if (check) {
					follow_item_el.innerHTML = `<button onClick="follow(false, ${userId}, ${i})" class="following_btn">팔로잉</button>`;
				} else {
					follow_item_el.innerHTML = `<button onClick="follow(true, ${userId}, ${i})" class="follow_btn">팔로우</button>`;
				}
			}
		},
		error: function (error) {
			console.error("Ajax error:", error);
		}
	});
}