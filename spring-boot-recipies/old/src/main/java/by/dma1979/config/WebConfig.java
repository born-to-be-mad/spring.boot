package by.dma1979.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Customization of the static resources.
 *
 * @author dzmitry.marudau
 * @since 2019.11
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**"
            )
            .addResourceLocations(
                "classpath:/META-INF/resources/webjars/",
                "classpath:/static/img/",
                "classpath:/static/css/",
                "classpath:/static/js/"
            );
    }

}
