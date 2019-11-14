package by.dma1979.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:34
 * @since : 2019.11
 **/
@Configuration
public class LibrarySecurityConfig extends WebSecurityConfigurerAdapter {

    public LibrarySecurityConfig() {
        super(true); // disable default configuration
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext() //security context integration.
                .and()
                .exceptionHandling() //exception handling
                .and()
                .servletApi(); // enable the Servlet API integration
    }

}
