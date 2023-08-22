package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.config.security.JWTConfig;
import fr.diginamic.aqiprojectbackend.dto.LoginDto;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("sessions")

public class SessionController {

    /** Web API for Authentication
     *    - using JWT token stored in AUTH-TOKEN cookie after successful login
     */
    private JWTConfig jwtConfig;
    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;

    public SessionController(JWTConfig jwtConfig, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.jwtConfig = jwtConfig;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authentication method:
     *    - capture given credentials from incoming request
     *    - create JWT Token from given credentials
     * @param loginDto credentials send from user via loginDto
     * @return : responseEntity
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody LoginDto loginDto) {
        return this.userAccountRepository.findByEmail(loginDto.getEmail())
                .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,buildJWTCookie(user)).build())
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Authentication cookie (AUTH-TOKEN) creation
     * @param user : user successfully connected
     * @return : JWT Token = cookie (as String)
     */
    private String buildJWTCookie(UserAccount user) {
        String jetonJWT = Jwts.builder()
                .setSubject(user.getEmail())
                .addClaims(Map.of("roles", user.getRole()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireIn() * 1000))
                .signWith(
                        jwtConfig.getSecretKey()
                ).compact();

        ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(), jetonJWT)
                .httpOnly(true)
                .maxAge(jwtConfig.getExpireIn() * 1000)
                .path("/")
                .build();
        System.out.println("tokenCookie" +tokenCookie + ";toString=" +tokenCookie.toString());
        return tokenCookie.toString();
    }
}
