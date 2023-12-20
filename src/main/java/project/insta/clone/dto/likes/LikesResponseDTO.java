package project.insta.clone.dto.likes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.insta.clone.domain.Likes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikesResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LikesResultDTO{
        private String username;
        private Long userId;
        private String caption;
        private String profileImage;
    }

    public static List<LikesResultDTO> toLikesListResultDTO(List<Likes> likes){
        List<LikesResultDTO> likesResultDTO = new ArrayList<>();

        return likes.stream()
                .map(l -> LikesResultDTO.builder()
                        .username(l.getUser().getUsername())
                        .userId(l.getUser().getUserId())
                        .caption(l.getImage().getCaption())
                        .profileImage(l.getUser().getProfileImage())
                        .build())
                .collect(Collectors.toList());
    }
}
