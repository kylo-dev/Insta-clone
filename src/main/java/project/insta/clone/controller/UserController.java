package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.User;
import project.insta.clone.dto.user.UserRequestDTO;
import project.insta.clone.service.user.UserCommandService;
import project.insta.clone.service.user.UserQueryService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

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

        // 1. User 조회
        User user = userQueryService.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Not Found User")
        );

        // 2. User imageCount
        int imageCount = user.getImages().size();
        model.addAttribute("imageCount", imageCount);

        // 3. followCount
        int followCount;

        return null;

    }
}
