package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.insta.clone.domain.Image;
import project.insta.clone.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

    int countByImage(Image image);
}
