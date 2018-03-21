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
public class UserConverterImpl implements UserConverter {

    @Override
    public BasicUser convertToUser(User user) {
        BasicUser basic = new BasicUser();
        basic.setUsername(user.getLogin());
        basic.setLastName(user.getLastname());
        basic.setFirstName(user.getFirstname());
        basic.setMail(user.getEmail());
        basic.setRoles(user.getMemberOf());
        return basic;
    }
}