package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;
import project.insta.clone.dto.ResponseDTO;
import project.insta.clone.dto.follow.FollowResponseDTO;
import project.insta.clone.service.follow.FollowCommandService;
import project.insta.clone.service.follow.FollowQueryService;
import project.insta.clone.service.user.UserQueryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final UserQueryService userQueryService;
    private final FollowCommandService followCommandService;
    private final FollowQueryService followQueryService;

    @PostMapping("/follow/{id}")
    public ResponseEntity<ResponseDTO.ResponseResultDTO> follow(@AuthenticationPrincipal PrincipalDetails principalDetails,
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
        return ResponseEntity.ok(new ResponseDTO.ResponseResultDTO(HttpStatus.OK, 1));
    }

    @DeleteMapping("/follow/{id}")
    public ResponseEntity<ResponseDTO.ResponseResultDTO> unfollow(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                    @PathVariable Long id){
        User fromUser = principalDetails.getUser();

        User toUser = userQueryService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found User")
        );

        followCommandService.unfollow(fromUser, toUser);

        return ResponseEntity.ok(new ResponseDTO.ResponseResultDTO(HttpStatus.OK, 1));
    }

    @GetMapping("/follow/follower/{id}")
    public String followFollower(@PathVariable Long id,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails,
                                 Model model){

        // 팔로워 리스트
        List<Follow> followerList = followQueryService.getFollowerList(id);

        // 팔로워 리스트 조회시, 그 중에 나와 팔로우 되어 있는지
        List<Follow> myFollowList = followQueryService.getFollowList(principalDetails.getUser().getUserId());

        // 로그인한 사용자와 팔로우 상태인지 확안하기
        for (Follow f1: followerList){
            for (Follow f2: myFollowList){
                if (f1.getFromUser().getUserId() == f2.getToUser().getUserId()){
                    f1.setFollowState(true);
                }
            }
        }

        model.addAttribute("followers", followerList);
        model.addAttribute("principal", principalDetails);
        return "follow/follower";
    }

    @GetMapping("/follow/follow/{id}")
    public String followFollow(@PathVariable Long id,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails,
                                 Model model){
        // 팔로우 리스트
        return "follow/follow";
    }
}
