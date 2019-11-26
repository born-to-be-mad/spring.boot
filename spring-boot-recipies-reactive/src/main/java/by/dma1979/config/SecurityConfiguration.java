package by.dma1979.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:24
 * @since : 2019.11
 **/
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/").permitAll()
                .pathMatchers("/orders*").hasRole("USER")
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetails user = userBuilder
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = userBuilder
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}
