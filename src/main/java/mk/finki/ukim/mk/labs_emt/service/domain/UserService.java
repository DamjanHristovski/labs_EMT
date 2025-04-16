package mk.finki.ukim.mk.labs_emt.service.domain;

import mk.finki.ukim.mk.labs_emt.model.Enum.Role;
import mk.finki.ukim.mk.labs_emt.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

}
