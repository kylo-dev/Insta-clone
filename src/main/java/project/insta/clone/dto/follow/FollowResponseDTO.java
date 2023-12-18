package project.insta.clone.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;

import java.time.LocalDateTime;

public class FollowResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FollowResultDTO{
        private Long followId;
        private Long fromUserId;
        private Long toUserId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnFollowResultDTO{
        private Long fromUserId;
        private Long toUserId;
        private LocalDateTime createdAt;
    }

    public static FollowResultDTO toFollowResultDTO(Follow follow){
        return FollowResultDTO.builder()
                .followId(follow.getFollowId())
                .fromUserId(follow.getFromUser().getUserId())
                .toUserId(follow.getToUser().getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UnFollowResultDTO toUnFollowResultDTO(User from, User to){
        return UnFollowResultDTO.builder()
                .fromUserId(from.getUserId())
                .toUserId(to.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
