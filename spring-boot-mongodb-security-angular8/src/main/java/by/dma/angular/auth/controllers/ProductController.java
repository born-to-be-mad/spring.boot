package by.dma.angular.auth.controllers;

import by.dma.angular.auth.models.Product;
import by.dma.angular.auth.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProductController {

	@Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    }
}