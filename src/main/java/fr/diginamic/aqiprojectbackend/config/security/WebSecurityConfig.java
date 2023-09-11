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

import static org.springframework.security.config.Customizer.withDefaults;

/** Web security Configuration */
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
     *  @throws Exception Any Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JWTAuthorizationFilter jwtFilter,
                                           JWTConfig jwtConfig) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth
                        // The following URLs should be available to all (no authentication required)
                        // URL used for login
                        .requestMatchers(
                                HttpMethod.POST,
                                "/sessions"
                        ).permitAll()

                        // URLs related to user accounts creation/management
                        .requestMatchers(
                                "/address", "address/*", "/addresses",
                                "/user-account", "/user-account/*",
                                "/bookmark", "/bookmark/*", "/bookmarks"
                        ).permitAll()

                        // URL related to the map section
                        .requestMatchers(
                                HttpMethod.GET,
                                "/air-quality-reports", "/air-quality-report/*",
                                "/air-quality-stations", "/air-quality-station/*",
                                "/cities", "/city/*",
                                "/departments", "/department/*",
                                "/forecast-types", "/forecast-type/*",
                                "/populations", "/population/*",
                                "/regions", "/region/*",
                                "/report-dates", "/report-date/*",
                                "/weather-reports", "/weather-report/*",
                                "/weather-stations", "/weather-station/*"
                        ).permitAll()

                        // URLs related to the forum section
                        // TO BE COMPLETED in next version
                        .requestMatchers(
                                HttpMethod.GET,
                                "/messages", "/message/*",
                                "/reactions", "/reaction/*",
                                "/threads", "/thread/*",
                                "/topics", "/topic/*"
                        ).permitAll()

                        //URLs to be restricted to ADMIN only
                        .requestMatchers(
                                "/user-status", "/user-status/*", "/user-statuses"
                        ).hasRole("ADMIN")

                        //Every other requests require authentication
                        .anyRequest().authenticated()
                    )

                // If Spring MVC is on classpath and no CorsConfigurationSource is provided,
                // Spring Security will use CORS configuration provided to Spring MVC.
                .cors(withDefaults())

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
