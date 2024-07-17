package net.uzmanfikir.staj1.services;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.UserDTO;
import net.uzmanfikir.staj1.enums.PermissionType;
import net.uzmanfikir.staj1.persistence.User;
import net.uzmanfikir.staj1.repository.UserDao;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> (org.springframework.security.core.userdetails.User) auth.getPrincipal())
                .map(UserDetails::getUsername)
                .flatMap(userDao::findByEmail);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class, ApplicationStartedEvent.class})
    public void onStartup() {
        if (userDao.findByEmail("admin@localhost").isEmpty()) {
            userDao.save(User.builder()
                    .email("admin@localhost")
                    .name("Admin user")
                    .password(passwordEncoder.encode("admin"))
                    .permissions(Arrays.asList(PermissionType.values()))
                    .build());
        }
    }

    public User create(UserDTO user) {
        return userDao.save(User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .permissions(user.getPermissions())
                .build());
    }

    public User update(UserDTO userToEdit) {
        var user = userDao.findById(userToEdit.getId()).orElseThrow();
        user.setName(userToEdit.getName());
        user.setEmail(userToEdit.getEmail());
        user.setPermissions(userToEdit.getPermissions());
        return userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public Optional<User> findById(Long id) {return userDao.findById(id);}
}
