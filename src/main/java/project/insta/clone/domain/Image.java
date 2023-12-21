package project.insta.clone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String location; // 사진 찍은 위치
    private String caption; // 사진 설명
    private String postImage; // 포스팅 사진 경로 + 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @Transient // DB에 영향을 주지 않음
    private int likeCount;

    @Transient
    private boolean heart;

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setHeart(boolean check){
        this.heart = check;
    }
    //== 연관관계 편의 메소드 ==//
    public void setUser(User user){
        this.user = user;
        user.getImages().add(this);
    }
}
