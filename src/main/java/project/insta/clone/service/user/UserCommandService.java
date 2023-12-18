package project.insta.clone.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.User;
import project.insta.clone.dto.user.UserRequestDTO;
import project.insta.clone.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void join(UserRequestDTO.JoinDTO joinDTO){
        String rawPassword = joinDTO.getPassword();

        User user = User.builder()
                .email(joinDTO.getEmail())
                .name(joinDTO.getName())
                .username(joinDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(rawPassword))
                .build();

        userRepository.save(user);
    }
}
