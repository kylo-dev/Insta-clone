package project.insta.clone.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.insta.clone.domain.User;
import project.insta.clone.dto.user.UserRequestDTO;
import project.insta.clone.repository.UserRepository;

import java.util.Optional;

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

    public void editUser(UserRequestDTO.EditDTO request){

        // 영속화
        User user = userRepository.findById(request.getUserId()).get();

        // 값 변경
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setWebsite(request.getWebsite());
        user.setBio(request.getBio());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
    }
}
