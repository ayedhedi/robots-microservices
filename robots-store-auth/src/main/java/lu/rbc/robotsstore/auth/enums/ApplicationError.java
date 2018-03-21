package lu.rbc.robotsstore.auth.enums;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public enum ApplicationError {

    GENERAL_TECHNICAL(1, ErrorType.TECHNICAL),

    USER_NOT_FOUND(10, ErrorType.BUSINESS),
    USER_INVALID_CREDENTIALS(11, ErrorType.BUSINESS),
    USER_NOT_ALLOWED(12, ErrorType.BUSINESS);

    private final long code;
    private final ErrorType type;

    ApplicationError(long code, ErrorType type) {
        this.code = code;
        this.type = type;
    }

    public long getCode() {
        return code;
    }

    public ErrorType getType() {
        return type;
    }
}