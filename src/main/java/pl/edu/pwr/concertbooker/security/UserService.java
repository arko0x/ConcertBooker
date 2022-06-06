package pl.edu.pwr.concertbooker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.security.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
//        User existUser = repo.findByUsername(username);
//
//        if (existUser == null) {
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setEmail(username);
//            newUser.setProvider(Provider.GOOGLE);
//            newUser.setEnabled(true);
//
//            repo.save(newUser);
//        }

    }

//    public User getCurrentUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String username;
//        String email;
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

//
//    }
}
