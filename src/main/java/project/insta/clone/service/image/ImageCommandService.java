package project.insta.clone.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Tag;
import project.insta.clone.domain.User;
import project.insta.clone.dto.image.ImageRequestDTO;
import project.insta.clone.repository.ImageRepository;
import project.insta.clone.repository.TagRepository;
import project.insta.clone.repository.UserRepository;
import project.insta.clone.util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageCommandService {

    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    @Value("${file.path}")
    private String fileRealPath;

    public void imageUpload(ImageRequestDTO.ImageUploadDTO request, Long userId) throws IOException{
        UUID uuid = UUID.randomUUID();
        MultipartFile file = request.getFile();

        String uuidFilename = uuid + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(fileRealPath + uuidFilename);
        Files.write(filePath, file.getBytes()); // 하드디스크 기록

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("Not Found User")
        );

        Image newImage = Image.builder()
                .location(request.getLocation())
                .caption(request.getCaption())
                .postImage(uuidFilename)
                .tags(new ArrayList<>()) // builder로 엔티티 생성시 초기화 하지 않으면 null값이 들어가게 됨
                .build();
        newImage.setUser(user);

        imageRepository.save(newImage);
        tagParsing(newImage.getImageId(), request.getTags());
    }

    public void tagParsing(Long imageId, String tags){

        Image image = imageRepository.findById(imageId).get();
        List<String> tagList = Utils.tagParser(tags);

        for (String tag : tagList){
            Tag imageTag = Tag.builder()
                    .name(tag)
                    .build();
            imageTag.setImage(image);
            tagRepository.save(imageTag);
        }
    }

    public void setImageLikeCount(Image image, int likeCount){
        image.setLikeCount(likeCount);
    }
}
