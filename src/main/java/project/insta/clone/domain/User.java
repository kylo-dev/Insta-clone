package project.insta.clone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username; // 사용자 아이디
    private String password;
    private String name; // 사용자 이름
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;
    private String profileImage; // 프로필 사진 경로 + 이름

    @OneToMany(mappedBy = "user")
    @OrderBy("imageId desc")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Likes> likes = new ArrayList<>();
}
