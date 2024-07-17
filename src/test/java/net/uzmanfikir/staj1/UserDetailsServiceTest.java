package net.uzmanfikir.staj1;

import net.uzmanfikir.staj1.enums.PermissionType;
import net.uzmanfikir.staj1.persistence.User;
import net.uzmanfikir.staj1.repository.UserDao;
import net.uzmanfikir.staj1.services.security.CremeBruleeUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CremeBruleeUserDetailsService.class})
public class UserDetailsServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserDao userDao;

    @Test
    void testUserDetails() {
        when(userDao.findByEmail(any())).thenReturn(Optional.of(User.builder()
                .name("Test User Fullname")
                .email("test@test.com")
                .password("test")
                .permissions(List.of(PermissionType.LOGIN, PermissionType.CUSTOMERS))
                .build()));
        final UserDetails testUser = userDetailsService.loadUserByUsername("test");
        assert testUser.isEnabled();
        assert testUser.getUsername().equals("test@test.com");
    }
}
