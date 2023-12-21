package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.insta.clone.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
