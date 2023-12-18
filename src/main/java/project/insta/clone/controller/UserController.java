package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.insta.clone.dto.user.UserRequestDTO;
import project.insta.clone.service.user.UserCommandService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

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
}
