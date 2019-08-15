package by.dma.angular.auth;

import by.dma.angular.auth.models.Product;
import by.dma.angular.auth.models.Role;
import by.dma.angular.auth.repositories.ProductRepository;
import by.dma.angular.auth.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class SpringAngularAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularAuthApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, ProductRepository productRepository) {

        return args -> {

            Optional.ofNullable(roleRepository.findByRole("ADMIN"))
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setRole("ADMIN");
                        roleRepository.save(newRole);
                        return newRole;
                    });
            Optional.ofNullable(roleRepository.findByRole("USER"))
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setRole("USER");
                        roleRepository.save(newRole);
                        return newRole;
                    });

			productRepository.findById("123")
					.orElseGet(() -> {
						Product newProduct = new Product();
						newProduct.setId("123");
						newProduct.setProdDesc("Test product");
						newProduct.setProdName("Super product");
						productRepository.save(newProduct);
						return newProduct;
					});
        };

    }
}
