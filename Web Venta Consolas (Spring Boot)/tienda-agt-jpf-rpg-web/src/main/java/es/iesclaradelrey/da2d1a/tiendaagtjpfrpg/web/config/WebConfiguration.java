package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    // Por alguna razon la consola no sale automaticamente a si que hay que hacer que salga
    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2ConsoleServlet() {
        // Exponemos manualmente la consola H2 en Boot 4.
        ServletRegistrationBean<JakartaWebServlet> registration =
                new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
        registration.setName("H2Console");
        registration.setLoadOnStartup(1);
        return registration;
    }
    @Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
        registry.addViewController("/condiciones").setViewName("condiciones");
        registry.addViewController("/condiciones/").setViewName("condiciones");
    }
}
