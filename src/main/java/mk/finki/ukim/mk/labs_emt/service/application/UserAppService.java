package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.CreateUserDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayUserDto;
import mk.finki.ukim.mk.labs_emt.dto.LoginUserDto;

import java.util.Optional;

public interface UserAppService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
