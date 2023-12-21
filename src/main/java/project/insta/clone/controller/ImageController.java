package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.dto.image.ImageRequestDTO;
import project.insta.clone.service.image.ImageCommandService;
import project.insta.clone.service.tag.TagCommandService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageCommandService imageCommandService;
    private final TagCommandService tagCommandService;

    @GetMapping("/image/feed")
    public String imageFeed(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        System.out.println("principalDetails = " + principalDetails.getUsername());
        model.addAttribute("principal", principalDetails);
        return "image/feed";
    }

    @GetMapping("/image/upload")
    public String imageUpload(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        model.addAttribute("principal", principalDetails);
        return "image/image_upload";
    }

    @PostMapping("/image/uploadProc")
    public String imageUploadProc(@ModelAttribute ImageRequestDTO.ImageUploadDTO request,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {

        imageCommandService.imageUpload(request, principalDetails.getUser().getUserId());
        return "redirect:/";
    }
}
