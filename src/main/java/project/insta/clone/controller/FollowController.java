package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;
import project.insta.clone.dto.follow.FollowResponseDTO;
import project.insta.clone.service.follow.FollowCommandService;
import project.insta.clone.service.user.UserQueryService;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final UserQueryService userQueryService;
    private final FollowCommandService followCommandService;

    @PostMapping("/follow/{id}")
    public @ResponseBody FollowResponseDTO.FollowResultDTO follow(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                  @PathVariable Long id){
        // 로그인한 사용자 정보
        User fromUser = principalDetails.getUser();

        // 팔로우 하는 사용자 정보
        User toUser = userQueryService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found User")
        );

        // follow 처리
        Follow follow = followCommandService.follow(fromUser, toUser);

        // converter
        return FollowResponseDTO.toFollowResultDTO(follow);
    }

    @DeleteMapping("/follow/{id}")
    public @ResponseBody FollowResponseDTO.UnFollowResultDTO unfollow(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                    @PathVariable Long id){
        User fromUser = principalDetails.getUser();

        User toUser = userQueryService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found User")
        );

        followCommandService.unfollow(fromUser, toUser);

        return FollowResponseDTO.toUnFollowResultDTO(fromUser, toUser);
    }
}