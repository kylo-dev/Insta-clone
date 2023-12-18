package project.insta.clone.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Image;
import project.insta.clone.repository.LikeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeQueryService {

    private final LikeRepository likeRepository;

    public int likeCount(Image image) {
        return likeRepository.countByImage(image);
    }
}
