package project.insta.clone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.insta.clone.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i " +
            "where i.user.userId in " +
            "(select f.toUser.userId from Follow f where f.fromUser.userId = :fromUserId)")
    Page<Image> findImage(@Param("fromUserId") Long fromUserId, Pageable pageable);

}
