package lu.rbc.robotsstore.query.configurations.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Hedi Ayed on 20/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Configuration
public class JWTConnectorSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {


    @Value("${security.jwt-key}")
    private String secretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .antMatcher("/store/query/robots/**")
                // Disable CSRF
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                // Stateless Session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // JWT authentication
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    private JwtFilter jwtFilter() {
        return new JwtFilter(secretKey);
    }

}
