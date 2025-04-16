package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.Enum.Role;
import mk.finki.ukim.mk.labs_emt.model.domain.User;

public record DisplayUserDto(
        String username,
        String name,
        String surname,
        Role role
) {
    public static DisplayUserDto from(User user)
    {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
    public User toUser()
    {
        return new User(username,name,surname,role.name());
    }
}
