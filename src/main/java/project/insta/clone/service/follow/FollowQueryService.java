package project.insta.clone.service.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;
import project.insta.clone.repository.FollowRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowQueryService {

    private final FollowRepository followRepository;

    public int followCheck(User fromUser, User toUser){
        return followRepository.countByFromUserAndToUser(fromUser, toUser);
    }

    public int followCount(User user){
        return followRepository.countByFromUser(user);
    }

    public int followerCount(User user){
        return followRepository.countByToUser(user);
    }

    public List<Follow> getFollowerList(Long id){
        return followRepository.findByToUserId(id);
    }

    public List<Follow> getFollowList(Long id){
        return followRepository.findByFromUserId(id);
    }



}
