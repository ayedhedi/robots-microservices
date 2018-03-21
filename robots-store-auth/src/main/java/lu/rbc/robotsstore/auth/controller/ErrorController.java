package lu.rbc.robotsstore.auth.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lu.rbc.robotsstore.auth.domain.dto.BasicError;
import lu.rbc.robotsstore.auth.domain.dto.LoginResult;
import lu.rbc.robotsstore.auth.enums.ApplicationError;
import lu.rbc.robotsstore.auth.exception.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@ControllerAdvice
@ResponseBody
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    private BasicError convertTo(ApplicationError applicationError) {
        BasicError result = new BasicError();
        result.setCode(applicationError.getCode());
        result.setType(applicationError.getType());
        return result;
    }

    @ExceptionHandler(value = {
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            MalformedJwtException.class,
            SignatureException.class,
            AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public LoginResult invalidLoginException(Exception e) {
        LOGGER.error("Invalid Token", e);
        LoginResult loginResult = new LoginResult();
        loginResult.setConnected(false);
        loginResult.setUsername("");
        loginResult.setRoles(new ArrayList<>(0));
        return loginResult;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasicError uncaughtException(Exception e) {
        LOGGER.error("Bad Request", e);
        return convertTo(ApplicationError.GENERAL_TECHNICAL);
    }
}