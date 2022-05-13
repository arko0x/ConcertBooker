package pl.edu.pwr.concertbooker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.security.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
        User existUser = repo.findUserByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setEnabled(true);

            repo.save(newUser);
        }

    }
}
