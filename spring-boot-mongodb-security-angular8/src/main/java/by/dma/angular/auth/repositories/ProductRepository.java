package by.dma.angular.auth.repositories;

import by.dma.angular.auth.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	@Override
    void delete(Product deleted);
}