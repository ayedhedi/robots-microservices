package lu.rbc.robotsstore.auth.exception;
import lombok.Getter;
import lu.rbc.robotsstore.auth.enums.ApplicationError;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Getter
public class AuthenticationException extends RuntimeException {

    private final ApplicationError applicationError;

    /**
     * Default Constructor.
     *
     * @param applicationError error
     */
    public AuthenticationException(ApplicationError applicationError) {
        this.applicationError = applicationError;
    }
}