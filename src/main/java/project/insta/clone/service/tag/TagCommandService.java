package project.insta.clone.service.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.Tag;
import project.insta.clone.repository.TagRepository;
import project.insta.clone.util.Utils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TagCommandService {

    private final TagRepository tagRepository;

}
