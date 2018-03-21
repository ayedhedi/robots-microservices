package lu.rbc.robotsstore.auth.service.impl;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultHeader;
import lu.rbc.robotsstore.auth.domain.model.User;
import lu.rbc.robotsstore.auth.enums.ApplicationError;
import lu.rbc.robotsstore.auth.exception.AuthenticationException;
import lu.rbc.robotsstore.auth.service.LdapService;
import lu.rbc.robotsstore.auth.service.UserService;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Service
public class UserServiceImpl implements UserService, InitializingBean {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.algorithm}")
    private String algorithm;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.issuer}")
    private String issuer;

    private final LdapService ldapService;

    private SignatureAlgorithm signatureAlgorithm;

    public UserServiceImpl(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @Override
    public void afterPropertiesSet() {
        signatureAlgorithm = SignatureAlgorithm.forName(algorithm);
        if (!signatureAlgorithm.isJdkStandard()) {
            throw new BeanCreationException("The Signature Algorithm is not included by default in JDK");
        }
    }

    @Override
    public User authenticate(String login, String password) {

        User user = ldapService.authenticate(login, password);

        // Prepare Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, user.getLogin());
        claims.put("given_name", user.getFirstname());
        claims.put("family_name", user.getLastname());
        claims.put("email", user.getEmail());
        claims.put("member", user.getMemberOf());
        claims.put("rights", user.getRights());

        user.setToken(generateToken(claims));

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User retrieveUserFromAuthorization(String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AuthenticationException(ApplicationError.USER_NOT_FOUND);
        }

        String token = authorization.substring(7);

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        User user = new User();
        user.setLogin(claims.getSubject());
        user.setLastname(claims.get("family_name", String.class));
        user.setFirstname(claims.get("given_name", String.class));
        user.setEmail(claims.get("email", String.class));
        user.setMemberOf((List<String>) claims.get("member"));
        user.setRights((List<String>) claims.get("rights"));
        return user;
    }


    private Date generateExpirationDate(Date date) {
        return new Date(date.getTime() + expiration * 1000);
    }

    private String generateToken(Map<String, Object> claims) {
        Date current = new Date();
        Header header = new DefaultHeader();
        header.setType(Header.JWT_TYPE);
        return Jwts.builder()
                .setHeader((Map<String, Object>) header)
                .setClaims(claims)
                .setIssuedAt(current)
                .setIssuer(issuer)
                .setExpiration(generateExpirationDate(current))
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

}