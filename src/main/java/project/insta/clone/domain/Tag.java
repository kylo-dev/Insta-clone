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
public class Tag extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId")
    private Image image;

    //== 연관관계 편의 메소드 ==//
    public void setImage(Image image){
        this.image = image;
        System.out.println("image = " + image.getTags());
        image.getTags().add(this);
    }
}
