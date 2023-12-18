package project.insta.clone.service.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;
import project.insta.clone.repository.FollowRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowCommandService {

    private final FollowRepository followRepository;

    public Follow follow(User fromUser, User toUser){
        Follow newFollow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .followState(true)
                .build();
        return followRepository.save(newFollow);
    }

    public void unfollow(User fromUser, User toUser){
        followRepository.deleteByFromUserAndToUser(fromUser, toUser);
    }
}
