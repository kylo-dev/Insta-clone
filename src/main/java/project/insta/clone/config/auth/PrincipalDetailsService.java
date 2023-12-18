package project.insta.clone.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.insta.clone.domain.User;
import project.insta.clone.service.user.UserQueryService;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userQueryService.findByUsername(username);

        UserDetails userDetails = null;

        if(user != null) {
            userDetails = new PrincipalDetails(user);
        } else{
            throw new UsernameNotFoundException("Not Found username");
        }
        return userDetails;
    }
}
