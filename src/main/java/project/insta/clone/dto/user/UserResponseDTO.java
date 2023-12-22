package project.insta.clone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.insta.clone.domain.User;

import java.time.LocalDateTime;

public class UserResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinResultDTO{
        Long userId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserFeedResultDTO{
        private String username;
        private String profileImage;
    }

    public static UserFeedResultDTO toUserFeedDTO(User user){
        return UserFeedResultDTO.builder()
                .username(user.getUsername())
                .profileImage(user.getProfileImage())
                .build();
    }
}
