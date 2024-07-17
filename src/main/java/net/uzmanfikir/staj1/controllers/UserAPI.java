package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.UserDTO;
import net.uzmanfikir.staj1.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Secured("ROLE_USERS")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;

    @GetMapping("/list")
    public List<UserDTO> listUsers() {
        return userService.getUsers().stream().map(UserDTO::of).toList();
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return UserDTO.of(userService.create(user));
    }

    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return UserDTO.of(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
