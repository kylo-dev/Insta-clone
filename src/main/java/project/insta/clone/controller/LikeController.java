package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.User;
import project.insta.clone.dto.likes.LikesResponseDTO;
import project.insta.clone.service.like.LikesCommandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikesCommandService likesCommandService;

    @GetMapping("/like/notification")
    public List<LikesResponseDTO.LikesResultDTO> likeNotification(@AuthenticationPrincipal PrincipalDetails principalDetails
            ){
        System.out.println("principalDetails = " + principalDetails);;
        User user = principalDetails.getUser();
        return likesCommandService.findLikeNotification(user.getUserId());
    }
}
