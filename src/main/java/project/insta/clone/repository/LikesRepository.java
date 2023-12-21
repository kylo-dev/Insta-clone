package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Likes;
import project.insta.clone.domain.User;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    // "_" 을 통해 엔티티 속성 접근 가능
    int countByImage_ImageId(Long imageId);

    int countByImage(Image image);

    // 내가 좋아요 한 이미지 찾기
    Likes findByUserAndImage(User user, Image image);

    @Query("select l from Likes l " +
            "where l.image.imageId in (select imageId from l.image where l.user.userId = :userId) " +
            "order by l.likeId desc limit 5")
    List<Likes> findLikeNotification(@Param("userId") Long userId);

    // 사용자가 해당 이미지에 좋아요 눌렀는지 확인하기
    Boolean existsByUserAndImage(User user, Image image);
}