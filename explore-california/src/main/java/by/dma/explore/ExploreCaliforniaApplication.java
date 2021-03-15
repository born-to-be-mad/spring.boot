package by.dma.explore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.dma.explore.domain.Difficulty;
import by.dma.explore.domain.Region;
import by.dma.explore.service.TourPackageService;
import by.dma.explore.service.TourService;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static by.dma.explore.ExploreCaliforniaApplication.TourFromFile.importTours;
import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@EnableSwagger2
public class ExploreCaliforniaApplication implements CommandLineRunner {

  private static final String INIT_DATA_FILE = "/initial_data.json";

  @Autowired
  private TourPackageService tourPackageService;

  @Autowired
  private TourService tourService;

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("by.dma.explore"))
            .paths(any())
            .build()
            .apiInfo(
                    new ApiInfo("Explore California API's",
                                "API's for the Explore California Travel Service",
                                "2.0",
                                null,
                                new Contact("Dzmitry Marudau",
                                            "https://github.com/dma1979",
                                            "vinmaster@tut.by"),
                                null,
                                null,
                                new ArrayList()));
  }

  public static void main(String[] args) {
    SpringApplication.run(ExploreCaliforniaApplication.class, args);
  }

  /**
   * Method invoked after this class has been instantiated by Spring container
   * Initializes the in-memory database with all the TourPackages and Tours.
   *
   * @param args
   * @throws Exception if problem occurs.
   */
  @Override
  public void run(String... args) throws Exception {
    //use json import
    if (args.length > 0 && "json".equalsIgnoreCase(args[0])) {
      //Create the default tour packages
      tourPackageService.createTourPackage("BC", "Backpack Cal");
      tourPackageService.createTourPackage("CC", "California Calm");
      tourPackageService.createTourPackage("CH", "California Hot springs");
      tourPackageService.createTourPackage("CY", "Cycle California");
      tourPackageService.createTourPackage("DS", "From Desert to Sea");
      tourPackageService.createTourPackage("KC", "Kids California");
      tourPackageService.createTourPackage("NW", "Nature Watch");
      tourPackageService.createTourPackage("SC", "Snowboard Cali");
      tourPackageService.createTourPackage("TC", "Taste of California");
      System.out.println("Number of tours packages =" + tourPackageService.total());

      //Persist the Tours to the database
      importTours().forEach(t -> tourService.createTour(
              t.title,
              t.description,
              t.blurb,
              Integer.parseInt(t.price),
              t.length,
              t.bullets,
              t.keywords,
              t.packageType,
              Difficulty.valueOf(t.difficulty),
              Region.findByLabel(t.region)));
      System.out.println("Number of tours =" + tourService.total());
    }


  }

  /**
   * Helper class to import the records in the ExploreCalifornia.json
   */
  static class TourFromFile {
    //attributes as listed in the .json file
    private String packageType;
    private String title;
    private String description;
    private String blurb;
    private String price;
    private String length;
    private String bullets;
    private String keywords;
    private String difficulty;
    private String region;

    /**
     * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
     *
     * @return a List of TourFromFile objects.
     * @throws IOException if ObjectMapper unable to open file.
     */
    static List<TourFromFile> importTours() throws IOException {
      return new ObjectMapper()
              .setVisibility(PropertyAccessor.FIELD,
                             JsonAutoDetect.Visibility.ANY)
              .readValue(TourFromFile.class.getResourceAsStream(INIT_DATA_FILE),
                         new TypeReference<List<TourFromFile>>() {
                         });
    }
  }

}
