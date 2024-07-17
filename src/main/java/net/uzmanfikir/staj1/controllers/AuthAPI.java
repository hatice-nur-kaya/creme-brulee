package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.UserDTO;
import net.uzmanfikir.staj1.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Secured("ROLE_LOGIN")
@RequiredArgsConstructor
public class AuthAPI {
    private final UserService userService;

    @GetMapping("/me")
    public UserDTO getCurrentUserInfo() {
        return userService.getCurrentUser().map(UserDTO::of).orElseThrow();
    }
}
