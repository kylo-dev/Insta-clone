package project.insta.clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Likes;
import project.insta.clone.domain.User;
import project.insta.clone.dto.ResponseDTO;
import project.insta.clone.dto.image.ImageRequestDTO;
import project.insta.clone.dto.image.ImageResponseDTO;
import project.insta.clone.service.image.ImageCommandService;
import project.insta.clone.service.image.ImageQueryService;
import project.insta.clone.service.like.LikesCommandService;
import project.insta.clone.service.like.LikesQueryService;
import project.insta.clone.service.tag.TagCommandService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageCommandService imageCommandService;
    private final ImageQueryService imageQueryService;
    private final LikesQueryService likesQueryService;
    private final LikesCommandService likesCommandService;

    @GetMapping({"/", "/image/feed"})
    public String imageFeed(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,
                            @PageableDefault(size = 3, sort = "imageId", direction = Sort.Direction.DESC)Pageable pageable){
        System.out.println("principalDetails = " + principalDetails.getUsername());

        // 1. 내가 팔로우한 친구들의 사진
        List<Image> images = imageQueryService.findImageFeed(pageable, principalDetails.getUser().getUserId());

        // 3. 좋아요 누른 이미지인지 체크하기
        for (Image image: images){
            Boolean like = likesQueryService.existLikeByUserAndImage(principalDetails.getUser(), image);
            if (like){
                image.setHeart(true);
            }
        }

        // 2. likeCount
        for (Image image: images){
            int likeCount = likesQueryService.countLikeCountByImageId(image.getImageId());
            imageCommandService.setImageLikeCount(image, likeCount);
        }

        model.addAttribute("principal", principalDetails);
        model.addAttribute("images", images);
        return "image/feed";
    }

    @GetMapping("/image/feed/scroll")
    public @ResponseBody List<ImageResponseDTO.ImageFeedResultDTO> imageFeedScroll(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,
                                                                @PageableDefault(size = 3, sort = "imageId", direction = Sort.Direction.DESC)Pageable pageable){
        List<Image> images = imageQueryService.findImageFeed(pageable, principalDetails.getUser().getUserId());

        for (Image image: images){
            Boolean like = likesQueryService.existLikeByUserAndImage(principalDetails.getUser(), image);
            if (like){
                image.setHeart(true);
            }

            int likeCount = likesQueryService.countLikeCountByImageId(image.getImageId());
            image.setLikeCount(likeCount);
        }
        imageQueryService.getImageFeeds(images);
        return imageQueryService.getImageFeeds(images);
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
    
    @PostMapping("/image/like/{imageId}")
    public ResponseEntity<ResponseDTO.ResponseResultDTO> imageLike(@PathVariable Long imageId,
                                                                   @AuthenticationPrincipal PrincipalDetails principalDetails){
        User principal = principalDetails.getUser();

        Image findImage = imageQueryService.findByImageId(imageId);
        Likes oldLike = likesQueryService.findByUserAndImage(principal, findImage);

        if(oldLike == null){
            likesCommandService.likeFeed(principal, findImage);
        } else{
            likesCommandService.deleteById(oldLike.getLikeId());
        }
        return ResponseEntity.ok(new ResponseDTO.ResponseResultDTO(HttpStatus.OK, (oldLike == null) ? 1 : 0));
    }
}
