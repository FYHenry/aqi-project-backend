package fr.diginamic.aqiprojectbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger =
            LoggerFactory.getLogger(AqiProjectBackendApplication.class);
    public static final String aqiVersion =
            getBundle("application").getString("project.version");
    /** Main method */
    public static void main(String[] args) {
        SpringApplication.run(AqiProjectBackendApplication.class, args);
        logger.info("AQI backend version : {}", aqiVersion);
        logger.info("Swagger UI : http://127.0.0.1:8081/swagger-ui/index.html");
        logger.info("Swagger API : http://127.0.0.1:8081/v3/api-docs");
    }
}
