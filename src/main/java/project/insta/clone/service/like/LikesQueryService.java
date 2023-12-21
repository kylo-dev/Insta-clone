package project.insta.clone.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.User;
import project.insta.clone.repository.LikesRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikesQueryService {

    private final LikesRepository likesRepository;

    public int likeCount(Image image) {
        return likesRepository.countByImage(image);
    }

    public int countLikeCountByImageId(Long imageId){
        return likesRepository.countByImage_ImageId(imageId);
    }

    public Boolean existLikeByUserAndImage(User user, Image image){
        return likesRepository.existsByUserAndImage(user, image);
    }
}
