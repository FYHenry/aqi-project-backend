package fr.diginamic.aqiprojectbackend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

import java.util.Map;

@Configuration
// Annotation to secure method access
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    /**
     * Bean Spring - type `PasswordEncoder` - to encrypt a password:
     *     - using BCrypt (BCryptPasswordEncoder)
     *     - using DelegatingPasswordEncoder to allow validation of passwords encoded using different methods
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        String encodingId = "bcrypt";
        return new DelegatingPasswordEncoder(encodingId, Map.of(encodingId, new BCryptPasswordEncoder()));
    }

    /**
     * Spring Security Filter setup
     *  @param http object given by Spring Security to set the configuration
     *  @param jwtFilter object given by Spring Security to set the configuration
     *  @param jwtConfig Spring Security object to retrieve jwt config values defined in application.properties
     *  @return SecurityFilterChain that will be applied by Spring Security.
     *  @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JWTAuthorizationFilter jwtFilter, JWTConfig jwtConfig) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth
                    // The following URLs should be available to all (no authentication required)
                    // URL used for login
                    .requestMatchers(HttpMethod.POST, "/sessions").permitAll()

                    // URLs related to user accounts creation/management
                    .requestMatchers("/address").permitAll()
                    .requestMatchers("/address/**").permitAll()
                    .requestMatchers("/addresses").permitAll()
                    .requestMatchers("/user-account").permitAll()
                    .requestMatchers("/user-account/**").permitAll()
                    .requestMatchers("/connectedUser/**").permitAll()
                    .requestMatchers("/bookmark").permitAll()
                    .requestMatchers("/bookmark/**").permitAll()
                    .requestMatchers("/bookmarks").permitAll()

                    // URL related to the map section
                    .requestMatchers(HttpMethod.GET, "/air-quality-reports").permitAll()
                    .requestMatchers(HttpMethod.GET, "/air-quality-report/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/air-quality-stations").permitAll()
                    .requestMatchers(HttpMethod.GET, "/air-quality-station/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/cities").permitAll()
                    .requestMatchers(HttpMethod.GET, "/city/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/departments").permitAll()
                    .requestMatchers(HttpMethod.GET, "/department/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/forecast-types").permitAll()
                    .requestMatchers(HttpMethod.GET, "/forecast-type/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/populations").permitAll()
                    .requestMatchers(HttpMethod.GET, "/population/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/regions").permitAll()
                    .requestMatchers(HttpMethod.GET, "/region/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/report-dates").permitAll()
                    .requestMatchers(HttpMethod.GET, "/report-date/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/weather-reports").permitAll()
                    .requestMatchers(HttpMethod.GET, "/weather-report/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/weather-stations").permitAll()
                    .requestMatchers(HttpMethod.GET, "/weather-stations/**").permitAll()

                    // URLs related to the forum section
                    // TO BE COMPLETED in next version

                    //URLs to be restricted to ADMIN only
                    .requestMatchers("/user-status").hasRole("ADMIN")
                    .requestMatchers("/user-status/**").hasRole("ADMIN")
                    .requestMatchers("/user-statuses").hasRole("ADMIN")

                    //Every other requests requires authentication
                    .anyRequest().authenticated()
                )

        //CSRF Configuration:
        // - using Spring Security XSRF-TOKEN
        // - a HTTP header "X-XSRF-TOKEN" needs to be send by the client for any creation/update requests
        .csrf(csrf -> csrf
             .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
             .csrfTokenRequestHandler(new XorCsrfTokenRequestAttributeHandler()::handle)
        )

        // Spring Security Filter activation
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

        // User deconnection via POST /logout: HTTPStatus set to OK; delete authentication cookie
        .logout(logout -> logout
            .logoutSuccessHandler((req,resp,auth) -> resp.setStatus(HttpStatus.OK.value()))
            .deleteCookies(jwtConfig.getCookie()));

        return http.build();
    }
}
