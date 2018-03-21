package lu.rbc.robotsstore.auth.converter;

import lu.rbc.robotsstore.auth.domain.dto.BasicUser;
import lu.rbc.robotsstore.auth.domain.model.User;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface UserConverter {
    BasicUser convertToUser(User user);
}
