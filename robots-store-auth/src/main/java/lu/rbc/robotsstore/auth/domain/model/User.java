package lu.rbc.robotsstore.auth.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */

@Getter
@Setter
public class User {

    private String login;
    private String lastname;
    private String firstname;
    private String email;
    private String displayName;
    private List<String> memberOf;
    private List<String> rights;
    private String token;

}