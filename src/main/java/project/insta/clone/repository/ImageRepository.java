package project.insta.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.insta.clone.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
