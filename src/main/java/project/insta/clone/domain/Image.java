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
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String location; // 사진 찍은 위치
    private String caption; // 사진 설명
    private String postImage; // 포스팅 사진 경로 + 이름
    @Transient // DB에 영향을 주지 않음
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "image")
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "image")
    private List<Tag> tags = new ArrayList<>();
}
