package lu.rbc.robotsstore.auth.service.impl;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

import lu.rbc.robotsstore.auth.domain.model.User;
import lu.rbc.robotsstore.auth.enums.ApplicationError;
import lu.rbc.robotsstore.auth.exception.AuthenticationException;
import lu.rbc.robotsstore.auth.service.LdapService;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Service
public class MockLdapServiceImpl implements LdapService {

    @Override
    public User authenticate(String login, String password) {
        User user;
        //TODO: this is just a mock !!
        if ( "admin".equals(login)) {
            if ("admin".equals(password)){
                user = mockAdminUser();
            }else {
                throw new AuthenticationException(ApplicationError.USER_INVALID_CREDENTIALS);
            }
        }else if ("user".equals(login)) {
            if ("user".equals(password)){
                user = mockSimpleUser();
            }else {
                throw new AuthenticationException(ApplicationError.USER_INVALID_CREDENTIALS);
            }
        }else {
            throw new AuthenticationException(ApplicationError.USER_NOT_FOUND);
        }

        return user;
    }

    private User mockAdminUser() {
        User user = new User();
        user.setEmail("admin@rbc.lu");
        user.setFirstname("Admin");
        user.setLastname("Admin");
        user.setLogin("admin");
        user.setMemberOf(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        user.setRights((Arrays.asList("ROLE_USER", "ROLE_ADMIN")));
        user.setDisplayName("Admin");
        return user;
    }

    private User mockSimpleUser() {
        User user = new User();
        user.setEmail("user@rbc.lu");
        user.setFirstname("User");
        user.setLastname("User");
        user.setLogin("user");
        user.setMemberOf(Collections.singletonList("ROLE_USER"));
        user.setRights((Collections.singletonList("ROLE_USER")));
        user.setDisplayName("User");
        return user;
    }
}
