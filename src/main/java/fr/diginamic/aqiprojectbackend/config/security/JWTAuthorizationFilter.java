package fr.diginamic.aqiprojectbackend.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
/** JWT Authorization Filter */
@Configuration
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    /**
     * JWT authorization Filter configuration:
     *    - define how authorization will be handled inside the application
     */
    private final JWTConfig jwtConfig;

    /**
     * Constructor with parameters.
     * @param jwtConfig JWT Configuration
     */
    public JWTAuthorizationFilter(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * Override doFilterInternal method:
     *    - capture incoming request
     *    - allow or block the request depending on configuration settings
     *  @param req (HttpServletRequest) .
     *  @param res (HttpServletResponse) .
     *  @param chain (FilterChain).
     *  @throws IOException I/O Exception
     *  @throws ServletException Servlet Exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // check if token is present
        if (req.getCookies() != null) {
            Stream.of(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(jwtConfig.getCookie()))
                    .map(Cookie::getValue)
                    .forEach(token -> {
                        Claims body = Jwts
                                .parserBuilder()
                                .setSigningKey(jwtConfig.getSecretKey())
                                .build()
                                .parseClaimsJws(token)
                                .getBody();

                        //read credentials from token
                        String email = body.getSubject();
                        String role = body.get("roles").toString();
                        //validate and verify token credentials
                        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, getAuthorities(role));
                        //complete authorizations
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });

        }
        chain.doFilter(req, res);

    }
    /**
     * Collection getAuthorities :
     *    - set a role from String format to list format since UsernamePasswordAuthenticationToken can only read lists of roles
     *  @param role : user role (from token) as a String.
     *  @return Collection of roles: user role (from token) as a list
     */
    private Collection<? extends SimpleGrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
