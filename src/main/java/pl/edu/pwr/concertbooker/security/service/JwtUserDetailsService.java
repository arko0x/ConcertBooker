package pl.edu.pwr.concertbooker.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        String email;

        return new User("user", "password", List.of(new SimpleGrantedAuthority("ROLE_USER")));
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//            return repo.findUserByUsername(username);
//        } else if (principal instanceof CustomOAuth2User) {
//            email = ((CustomOAuth2User)principal).getEmail();
//            return repo.findByEmail(email);
//        } else {
//            username = principal.toString();
//            return repo.findUserByUsername(username);
//        }


    }
}