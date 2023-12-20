package project.insta.clone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {

    @Data // Setter 가 있어야 form 데이터를 바인딩할 수 있음
    @NoArgsConstructor
    public static class JoinDTO{
        private String email;
        private String name;
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    public static class EditDTO{
        private Long userId;
        private String name;
        private String username;
        private String website;
        private String bio;
        private String email;
        private String phone;
        private String gender;
    }
}
