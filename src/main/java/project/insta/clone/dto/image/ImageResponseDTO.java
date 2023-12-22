package project.insta.clone.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Tag;
import project.insta.clone.dto.tag.TagResponseDTO;
import project.insta.clone.dto.user.UserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ImageResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageFeedResultDTO{
        private Long imageId;
        private String location;
        private String caption;
        private String postImage;
        private boolean heart;
        private int likeCount;
        private UserResponseDTO.UserFeedResultDTO user;
        private List<TagResponseDTO.TagFeedResultDTO> tags;
    }

    public static List<ImageFeedResultDTO> toImageFeedDTOList(List<Image> images){
        return images.stream()
                .map(img -> ImageFeedResultDTO.builder()
                        .imageId(img.getImageId())
                        .location(img.getLocation())
                        .caption(img.getCaption())
                        .postImage(img.getPostImage())
                        .heart(img.isHeart())
                        .likeCount(img.getLikeCount())
                        .user(UserResponseDTO.toUserFeedDTO(img.getUser()))
                        .tags(TagResponseDTO.toTagFeedDTOList(img.getTags()))
                        .build())
                .collect(Collectors.toList());
    }

}
