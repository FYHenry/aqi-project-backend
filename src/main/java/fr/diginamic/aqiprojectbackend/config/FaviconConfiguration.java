package fr.diginamic.aqiprojectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Collections;
import java.util.List;

@Configuration
public class FaviconConfiguration {
    private final String resourceDir = "static/";
    private final String imageFile = "favicon.ico";
    @Bean
    protected ResourceHttpRequestHandler getFaviconRequestHandler() {
        final ResourceHttpRequestHandler requestHandler =
                new ResourceHttpRequestHandler();
        final List<Resource> locations =
                List.of(new ClassPathResource(resourceDir));
        requestHandler.setLocations(locations);
        return requestHandler;
    }
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
