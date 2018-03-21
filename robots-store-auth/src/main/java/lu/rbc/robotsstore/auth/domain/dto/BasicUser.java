package lu.rbc.robotsstore.auth.domain.dto;

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
public class BasicUser {

    private String username;
    private String firstName;
    private String lastName;
    private String mail;
    private List<String> roles;

}