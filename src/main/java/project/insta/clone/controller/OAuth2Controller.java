package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.insta.clone.service.user.UserCommandService;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {

    private final UserCommandService userCommandService;

    @GetMapping("/auth/kakao/callback")
    public String kakaoLogin(){

        return null;
    }

}
