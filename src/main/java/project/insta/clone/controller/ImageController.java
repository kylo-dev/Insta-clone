package project.insta.clone.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.insta.clone.config.auth.PrincipalDetails;

@Controller
public class ImageController {

    @GetMapping("/image/feed")
    public String imageFeed(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        System.out.println("principalDetails = " + principalDetails.getUsername());
        model.addAttribute("principal", principalDetails);
        return "image/feed";
    }
}
