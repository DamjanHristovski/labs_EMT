package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.Enum.Role;
import mk.finki.ukim.mk.labs_emt.model.domain.User;


public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser()
    {
        return new User(username,password,name,surname,role);
    }
}
