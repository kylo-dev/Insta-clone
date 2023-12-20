package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.insta.clone.domain.Follow;
import project.insta.clone.domain.User;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // unfollow
    void deleteByFromUserAndToUser(User fromUser, User toUser);

    // follow check
    int countByFromUserAndToUser(User fromuser, User toUser);

    // follow list
    List<Follow> findByFromUser(User fromUser);

    @Query("select f from Follow f " +
            "where f.fromUser.userId = :userId")
    List<Follow> findByFromUserId(@Param("userId") Long userId);

    // follower list
    List<Follow> findByToUser(User toUser);

    @Query("select f from Follow f " +
            "where f.toUser.userId = :userId")
    List<Follow> findByToUserId(@Param("userId") Long userId);

    // follow count
    int countByFromUser(User fromUser);

    // follower count
    int countByToUser(User toUser);
}
