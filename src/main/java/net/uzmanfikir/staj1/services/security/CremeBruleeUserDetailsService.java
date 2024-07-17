package net.uzmanfikir.staj1.services.security;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.repository.UserDao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CremeBruleeUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getPermissions()
                        .stream()
                        .map(perm -> new SimpleGrantedAuthority("ROLE_" + perm.name()))
                        .collect(Collectors.toSet()));
    }
}
