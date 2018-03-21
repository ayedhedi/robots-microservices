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
public class LoginResult {

    private String username;
    private List<String> roles;
    private boolean connected;
    private String token;

}