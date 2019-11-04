package by.dma1979;

import by.dma1979.calculator.Calculator;
import by.dma1979.entity.Book;
import by.dma1979.exception.CustomizedErrorAttributes;
import by.dma1979.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class SpringBootRecipesApplication implements WebMvcConfigurer {

    private static final Logger LOG = LogManager.getLogger(SpringBootRecipesApplication.class);

    /*
    Changing a User's Locale.
    We register LocaleChangeInterceptor as interceptor via WebMvcConfigurer.
    @param registry the registry of interceptors
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecipesApplication.class, args);
        LOG.info("Hello from Log4j 2 - ConfigurableApplicationContext : {}", () -> context);
        System.out.println("###############   BOOTING........");
        if (args.length > 0 && args[0].equalsIgnoreCase("-debug")) {
            printBeanDefinitions(context);
        }

        LOG.info("###############   THE END    ###############");
    }

    private static void printBeanDefinitions(ConfigurableApplicationContext context) {
        System.out.printf("# There are %d bean definitions:%n", context.getBeanDefinitionCount());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).forEach(System.out::println);
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    /*
    CookieLocaleResolver resolves locales by inspecting a cookie in a user’s browser.<BR/>
    If the cookie does not exist, this locale resolver determines the default locale from the Accept-Language HTTP header.
    */
    @Bean
    public LocaleResolver localeResolver() {
/*        AbstractLocaleContextResolver localeResolver = new SessionLocaleResolver(); // FixedLocaleResolver
        localeResolver.setDefaultLocale(new Locale("ru"));
        return localeResolver;*/

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        //we can customize
        //cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setCookieMaxAge(3600); //1 hour
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }

    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        System.out.println("### Initializing books ...");
        return args -> {
            bookService.create(
                    new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(
                    new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(
                    new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator,
                                               @Value("${lhs}") int lhs,
                                               @Value("${rhs}") int rhs,
                                               @Value("${op:+}") char op) {
        System.out.println("### Calculator calculate ...");
        return args -> calculator.calculate(lhs, rhs, op);
    }

    @Bean
    public CustomizedErrorAttributes errorAttributes() {
        return new CustomizedErrorAttributes();
    }

}
