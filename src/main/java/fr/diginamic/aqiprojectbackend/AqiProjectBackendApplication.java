package fr.diginamic.aqiprojectbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.ResourceBundle.getBundle;


/** Spring Boot application */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "AQI Project Rest API",
                description = "Une API Rest pour AQI",
                summary = "En gros des requêtes HTTP dont a accès un serveur " +
                        " Angular"
        )
)
public class AqiProjectBackendApplication {
    public static final String aqiVersion =
            getBundle("application").getString("aqi.version");
    /** Main method */
    public static void main(String[] args) {
        SpringApplication.run(AqiProjectBackendApplication.class, args);
    }
}
