package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.labs_emt.dto.CreateUserDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayUserDto;
import mk.finki.ukim.mk.labs_emt.dto.LoginUserDto;
import mk.finki.ukim.mk.labs_emt.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.labs_emt.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.labs_emt.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.labs_emt.service.application.impl.UserAppServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "User authentication and registration")
public class UserController {

    private final UserAppServiceImpl userAppService;

    public UserController(UserAppServiceImpl userAppService) {
        this.userAppService = userAppService;
    }


    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto)
    {
        try{
            return userAppService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (InvalidArgumentsException | PasswordsDoNotMatchException exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    public ResponseEntity<DisplayUserDto> login(HttpServletRequest request)
    {
        try{
            DisplayUserDto displayUserDto = userAppService.login(
                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
            ).orElseThrow(InvalidUserCredentialsException::new);

            request.getSession().setAttribute("user",displayUserDto.toUser());
            return ResponseEntity.ok(displayUserDto);

        }catch (InvalidUserCredentialsException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    @Operation(summary = "User logout", description = "Ends the session for the user")
    public void logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
    }
}
