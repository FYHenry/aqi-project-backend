package fr.diginamic.aqiprojectbackend.config.security;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/** JWT Configuration */
@Configuration
public class JWTConfig {
    /** Expires in */
    @Value("${jwt.expires_in}")
    private long expireIn;
    /** Cookie */
    @Value("${jwt.cookie}")
    private String cookie;
    /** Secret */
    @Value("${jwt.secret}")
    private String secret;
    /** Secret key */
    private Key secretKey;

    /**
     * Spring Security - JWT
     *   - authentication setup based on JWT token (JSON Web Token)
     *   - this class retrieves jwt config values from application.properties
     */
    @PostConstruct
    public void buildKey() {
        secretKey = new SecretKeySpec(Base64.getDecoder().decode(getSecret()),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Key getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Key secretKey) {
        this.secretKey = secretKey;
    }
}
