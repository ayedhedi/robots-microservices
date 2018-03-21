package lu.rbc.robotsstore.auth.service;

import lu.rbc.robotsstore.auth.domain.model.User;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface UserService {
    User authenticate(String login, String password);

    @SuppressWarnings("unchecked")
    User retrieveUserFromAuthorization(String authorization);
}
