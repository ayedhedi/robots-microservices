package lu.rbc.robotsstore.auth.domain.dto;
import lombok.Setter;
import lu.rbc.robotsstore.auth.enums.ErrorType;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */

@Setter
public class BasicError {
    private long code;
    private ErrorType type;

}