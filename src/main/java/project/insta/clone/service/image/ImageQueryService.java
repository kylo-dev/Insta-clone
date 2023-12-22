package project.insta.clone.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Image;
import project.insta.clone.dto.image.ImageResponseDTO;
import project.insta.clone.repository.ImageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageQueryService {

    private final ImageRepository imageRepository;

    public List<Image> findImageFeed(Pageable pageable, Long userId){
        Page<Image> imageList = imageRepository.findImage(userId, pageable);
        return imageList.getContent();
    }

    public List<ImageResponseDTO.ImageFeedResultDTO> getImageFeeds(List<Image> images){
        return ImageResponseDTO.toImageFeedDTOList(images);
    }

    public Image findByImageId(Long id){
        return imageRepository.findById(id).get();
    }
}
