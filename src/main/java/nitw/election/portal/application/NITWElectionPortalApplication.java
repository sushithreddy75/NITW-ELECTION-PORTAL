package nitw.election.portal.application;

//importing spring framework
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//for allowing CORS
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;


/*
 *   author @sushithreddy75
 *
 *   This class has the methods which start the spring boot application
 *   app.run starts the spring boot application on tomcat server
 *   The WebMvcConfigurer with bean annotation runs before the main method
 *   this method allows the CORS for the rest end points created
 */
@SpringBootApplication // Spring boot annotation
public class NITWElectionPortalApplication {
    public static void main(String... args) {
//        SpringApplication.run(testAppl.class, args);
        // CREATING A SPRING BOOT APPLICATION
        SpringApplication app = new SpringApplication(NITWElectionPortalApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }
    // @bean annotation is executed before the start of the program

    @Bean
    public WebMvcConfigurer corsConfigurer() {    // to allow CORS
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // allowing all the origins in CORS
                registry.addMapping("/**");
//                        .allowedOrigins("http://localhost:8080");
            }
        };
    }
}
