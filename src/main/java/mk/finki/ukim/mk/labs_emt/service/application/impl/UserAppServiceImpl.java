package mk.finki.ukim.mk.labs_emt.service.application.impl;

import mk.finki.ukim.mk.labs_emt.dto.CreateUserDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayUserDto;
import mk.finki.ukim.mk.labs_emt.dto.LoginUserDto;
import mk.finki.ukim.mk.labs_emt.model.domain.User;
import mk.finki.ukim.mk.labs_emt.service.application.UserAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService {
    private final UserService userService;

    public UserAppServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
