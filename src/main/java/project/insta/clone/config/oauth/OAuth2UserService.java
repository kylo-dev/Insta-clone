package project.insta.clone.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.insta.clone.config.auth.PrincipalDetails;
import project.insta.clone.config.oauth.provider.KakaoUserInfo;
import project.insta.clone.config.oauth.provider.OAuth2UserInfo;
import project.insta.clone.domain.User;
import project.insta.clone.repository.UserRepository;

import java.sql.SQLOutput;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo userInfo = null;
        System.out.println(oAuth2User.getAttributes());
        System.out.println(userRequest.getClientRegistration().getRegistrationId());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("kakao")){
            userInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else{
            System.out.println("카카오 로그인만 제공합니다.");
            throw new OAuth2AuthenticationException(new OAuth2Error("unsupported_provider"));
        }

        String provider = userInfo.getProvider();
        String providerId = userInfo.getProviderId();
        String oauth2Id = provider + "_" + providerId; // 중복 방지
        String username = userInfo.getName();
        String email = userInfo.getEmail();

        User findUser = userRepository.findByUsername(oauth2Id);

        if (findUser == null) {
            findUser = User.builder()
                        .username(oauth2Id)
                        .password(encoder.encode("miapassword"))
                        .email(email)
                        .name(username)
                        .provider(provider)
                        .providerId(providerId)
                        .build();
            userRepository.save(findUser);
        }
        return new PrincipalDetails(findUser, oAuth2User.getAttributes());
    }
}
