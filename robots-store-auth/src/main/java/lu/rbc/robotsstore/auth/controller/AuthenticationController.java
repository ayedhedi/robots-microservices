package lu.rbc.robotsstore.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import lu.rbc.robotsstore.auth.converter.UserConverter;
import lu.rbc.robotsstore.auth.converter.UserConverterImpl;
import lu.rbc.robotsstore.auth.domain.dto.BasicUser;
import lu.rbc.robotsstore.auth.domain.dto.LoginResult;
import lu.rbc.robotsstore.auth.domain.model.User;
import lu.rbc.robotsstore.auth.service.UserService;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@RestController
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthenticationController {

    private static final UserConverter USER_CONVERTER = new UserConverterImpl();

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/auth/login", consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoginResult> login(@RequestBody MultiValueMap body) {
        User user = userService.authenticate((String) body.getFirst("username"), (String) body.getFirst("password"));
        LoginResult result = new LoginResult();
        result.setUsername((String) body.getFirst("username"));
        result.setConnected(true);
        result.setRoles(user.getMemberOf());
        result.setToken("Bearer " + user.getToken());

        return new ResponseEntity<>(
                result,
                HttpStatus.OK);
    }

    @GetMapping(value = "/auth/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResult> logout() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "");
        LoginResult result = new LoginResult();
        result.setConnected(false);
        result.setUsername("");
        result.setRoles(new ArrayList<>(0));
        return new ResponseEntity<>(
                result,
                headers,
                HttpStatus.OK);
    }

    @GetMapping(value = "/auth/current",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasicUser currentUser(@RequestHeader(name = "Authorization", required = false) String authorization) {
        User user = userService.retrieveUserFromAuthorization(authorization);
        return USER_CONVERTER.convertToUser(user);
    }

}