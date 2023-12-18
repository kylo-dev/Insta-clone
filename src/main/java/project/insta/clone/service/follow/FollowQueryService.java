package project.insta.clone.service.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.insta.clone.repository.FollowRepository;

@Service
@RequiredArgsConstructor
public class FollowQueryService {

    private final FollowRepository followRepository;


}
