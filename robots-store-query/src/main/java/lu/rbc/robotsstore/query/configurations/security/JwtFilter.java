package lu.rbc.robotsstore.query.configurations.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public class JwtFilter extends GenericFilterBean {

    private final String secretKey;

    /**
     * Constructor.
     *
     * @param secretKey Secret Key to read the JWT
     */
    JwtFilter(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UsernameNotFoundException("Token not found: "+authorization);
        }

        String token = authorization.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        JwtToken jwtToken = new JwtToken(claims);
        try {
            SecurityContextHolder.getContext().setAuthentication(jwtToken);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}