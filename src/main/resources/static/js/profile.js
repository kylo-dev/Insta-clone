function followAjax(check, userId){
	let url = "/follow/" + userId;
	let method = check ? "POST" : "DELETE";

	$.ajax({
		url: url,
		method: method,
		success: function (data){
			if (data === "ok") {
				let follow_check_el = document.querySelector("#follow_check");
				if (check) {
					follow_check_el.html("<button onclick='followAjax(false, " + userId + ")' class='profile_edit_btn'>팔로잉</button>");
				} else {
					follow_check_el.html("<button onclick='followAjax(true, " + userId + ")' class='profile_follow_btn'>팔로우</button>");
				}
			}
		},
		error: function (error){
			console.error("Ajax error:", error);
		}
	});
}