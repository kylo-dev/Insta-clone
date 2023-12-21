package project.insta.clone.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class ImageRequestDTO {

    @Data
    @NoArgsConstructor
    public static class ImageUploadDTO{
        private MultipartFile file;
        private String caption;
        private String location;
        private String tags;
    }
}
