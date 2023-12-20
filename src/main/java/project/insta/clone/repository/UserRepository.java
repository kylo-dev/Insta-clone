package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.insta.clone.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
