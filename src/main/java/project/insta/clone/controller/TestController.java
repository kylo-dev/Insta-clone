package project.insta.clone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "home";
    }

    @GetMapping("/test/explore")
    public String testExplore(){
        return "image/explore";
    }
}
