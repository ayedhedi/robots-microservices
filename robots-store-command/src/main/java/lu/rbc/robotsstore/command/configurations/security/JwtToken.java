package lu.rbc.robotsstore.command.configurations.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public class JwtToken implements Authentication {

    // We can declare Claims as transient because we are in a stateless environment
    private final transient Claims claims;
    private final Collection<GrantedAuthority> authorities;
    private boolean authenticated = false;

    @SuppressWarnings("unchecked")
    public JwtToken(Claims claims) {
        this.claims = claims;
        // Filled the Authorities
        List<String> roles = (List<String>) claims.get("member");
        List<String> rights = (List<String>) claims.get("rights");
        List<GrantedAuthority> tmp = new ArrayList<>(roles.size() + rights.size());
        tmp.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));
        tmp.addAll(rights.stream().map(right -> new SimpleGrantedAuthority("RIGHT_" + right)).collect(Collectors.toList()));
        this.authorities = Collections.unmodifiableCollection(tmp);
        setAuthenticated(true);
    }

    @Override
    public String getName() {
        return claims.getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }
}