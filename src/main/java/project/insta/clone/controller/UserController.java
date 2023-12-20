package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.User;
import project.insta.clone.dto.ResponseDTO;
import project.insta.clone.dto.user.UserRequestDTO;
import project.insta.clone.service.follow.FollowQueryService;
import project.insta.clone.service.like.LikesQueryService;
import project.insta.clone.service.user.UserCommandService;
import project.insta.clone.service.user.UserQueryService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final FollowQueryService followQueryService;
    private final LikesQueryService likeQueryService;

    @GetMapping("/auth/login")
    public String authLogin(){
        return "auth/login";
    }

    @GetMapping("/auth/join")
    public String authJoin(){
        return "auth/join";
    }

    @PostMapping("/auth/joinProc")
    public String authJoinProc(@ModelAttribute UserRequestDTO.JoinDTO joinDTO){

        System.out.println(joinDTO);
        userCommandService.join(joinDTO);
        return "redirect:/auth/login";
    }

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails,
                          Model model){
        System.out.println("principalDetails = " + principalDetails);
        /**
         * 1. imageCount - 게시글 개수
         * 2. followerCount - 팔로워 개수
         * 3. followingCount - 팔로잉 개수
         * 4. User Object & Image 컬렉션
         * 5. followCheck - 팔로우 유무 ( 1 : 팔로우 / 0: 언팔로우)
         */

        // 4. User 조회
        User fromUser = principalDetails.getUser();

        User toUser = userQueryService.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Not Found User")
        );

        int imageCount = toUser.getImages().size();

        int followCount = followQueryService.followCount(toUser);

        int followerCount = followQueryService.followerCount(toUser);

        int followCheck = followQueryService.followCheck(fromUser, toUser);

        for (Image image : toUser.getImages()){
            int likeCount = likeQueryService.likeCount(image);
            image.setLikeCount(likeCount);
        }

        model.addAttribute("user", toUser);
        model.addAttribute("principal", principalDetails);
        model.addAttribute("imageCount", imageCount);
        model.addAttribute("followCount", followCount);
        model.addAttribute("followerCount", followerCount);
        model.addAttribute("followCheck", followCheck);
        return "user/profile";
    }

    @GetMapping("/user/edit")
    public String userEdit(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        User user = userQueryService.findById(principalDetails.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Not Found User"));

        model.addAttribute("user", user);
        model.addAttribute("principal", principalDetails);
        return "user/profile_edit";
    }

    @PatchMapping("/user/editProc")
    public ResponseEntity<ResponseDTO.ResponseResultDTO> userEditProc(@RequestBody UserRequestDTO.EditDTO request){

        userCommandService.editUser(request);

        return ResponseEntity.ok(new ResponseDTO.ResponseResultDTO(HttpStatus.OK, 1));
    }
}
