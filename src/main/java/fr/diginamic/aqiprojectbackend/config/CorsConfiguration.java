package fr.diginamic.aqiprojectbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        final String[] postUrls = {
                "/sessions"
        };
        final String[] anyUrls = {
                "/address", "address/**", "/addresses",
                "/user-account", "/user-account/**",
                "/bookmark", "/bookmark/**", "/bookmarks"
        };
        final String[] getUrls = {
                "/air-quality-reports", "/air-quality-report/**",
                "/air-quality-stations", "/air-quality-station/**",
                "/cities", "/city/**",
                "/departments", "/department/**",
                "/forecast-types", "/forecast-type/**",
                "/populations", "/population/**",
                "/regions", "/region/**",
                "/report-dates", "/report-date/**",
                "/weather-reports", "/weather-report/**",
                "/weather-stations", "/weather-stations/**"
        };
        final String[] adminUrls = {
                "/user-status", "/user-status/**", "/user-statuses"
        };

        for(String url: postUrls){
            registry
                    .addMapping(url)
                    .allowedMethods("POST");
        }

        for(String url: anyUrls){
            registry
                    .addMapping(url)
                    .allowedMethods("POST", "GET", "PUT", "DELETE");
        }

        for(String url: getUrls){
            registry
                    .addMapping(url)
                    .allowedMethods("GET");
        }

        for(String url: adminUrls){
            registry
                    .addMapping(url)
                    .allowedMethods("POST", "GET", "PUT", "DELETE");
        }
    }
}
