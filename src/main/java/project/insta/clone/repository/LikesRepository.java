package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Likes;
import project.insta.clone.domain.User;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    int countByImage(Image image);

    // 내가 좋아요 한 이미지 찾기
    Likes findByUserAndImage(User user, Image image);

    @Query("select l from Likes l " +
            "where l.image.imageId in (select imageId from l.image where l.user.userId = :userId) " +
            "order by l.likeId desc limit 5")
    List<Likes> findLikeNotification(@Param("userId") Long userId);
}