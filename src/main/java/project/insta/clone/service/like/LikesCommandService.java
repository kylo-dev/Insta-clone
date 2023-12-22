package project.insta.clone.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Likes;
import project.insta.clone.domain.User;
import project.insta.clone.dto.likes.LikesResponseDTO;
import project.insta.clone.repository.LikesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesCommandService {

    private final LikesRepository likesRepository;

    public List<LikesResponseDTO.LikesResultDTO> findLikeNotification(Long userId){
        List<Likes> likes = likesRepository.findLikeNotification(userId);

        return LikesResponseDTO.toLikesListResultDTO(likes);
    }

    public void deleteById(Long id){
        likesRepository.deleteById(id);
    }

    public void likeFeed(User user, Image image){
        Likes like = Likes.builder()
                .user(user)
                .image(image)
                .build();
        likesRepository.save(like);
    }
}
