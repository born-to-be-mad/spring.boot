package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * This aap out of the box do the following:
 * 1. Start an embedded Tomcat server on port 8080 (by default)
 * 2. Register and enable a couple of default Servlet Filters (Table 3-1)
 * 3. Set up static resource handling for things like .css, .js and
 *    favicon.ico
 * 4. Enable integration with WebJars2
 * 5. Setup basic error handling features
 * 6. Preconfigure the DispatcherServlet with the needed
 *     components (i.e., ViewResolvers, I18N, etc.)
 *
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class MvcDemoApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(MvcDemoApplication.class, args);
  }
}
