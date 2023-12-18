package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

    // follower list
    List<Follow> findByToUser(User toUser);

    // follow count
    int countByFromUser(User fromUser);

    // follower count
    int countByToUser(User toUser);
}
