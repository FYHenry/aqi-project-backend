package fr.diginamic.aqiprojectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Collections;
import java.util.List;
/** Favicon configuration */
@Configuration
public class FaviconConfiguration {
    /** Resource directory */
    private final String resourceDir = "static/";
    /** Favicon file */
    private final String imageFile = "favicon.ico";
    /** Request Handler */
    @Bean
    protected ResourceHttpRequestHandler getFaviconRequestHandler() {
        final ResourceHttpRequestHandler requestHandler =
                new ResourceHttpRequestHandler();
        final List<Resource> locations =
                List.of(new ClassPathResource(resourceDir));
        requestHandler.setLocations(locations);
        return requestHandler;
    }
    /** Handler mapping */
    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        final SimpleUrlHandlerMapping mapping =
                new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap(imageFile,
                getFaviconRequestHandler()));
        return mapping;
    }
}
