package by.dma1979.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:34
 * @since : 2019.11
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    public SecurityConfig() {
        super(true); // disable default configuration
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext() //security context integration.
                .and()
                .authorizeRequests().anyRequest().authenticated() //every request requires the user to be authenticated
                .and()
                .exceptionHandling() //exception handling
                .and()
                .httpBasic() // HTTP Basic Authentication is supported
                .and()
                .formLogin().defaultSuccessUrl("/") // form based authentication is supported
                .and()
                .logout().logoutSuccessUrl("/") // logout service provides a handler to handle logout requests
                .and()
                .anonymous().principal("guest").authorities("ROLE_GUEST") //Anonymous Login
                .and()
                .headers(); // the browser will be instructed to not cache the page.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin#")
                .authorities("ADMIN", "USER").build();
        UserDetails normalUser = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .authorities("USER").build();
        UserDetails disabledUser = User.withDefaultPasswordEncoder()
                .username("duser")
                .password("user")
                .disabled(true)
                .authorities("USER").build();

        auth.inMemoryAuthentication()
                .withUser(adminUser)
                .withUser(normalUser)
                .withUser(disabledUser);
    }

}
