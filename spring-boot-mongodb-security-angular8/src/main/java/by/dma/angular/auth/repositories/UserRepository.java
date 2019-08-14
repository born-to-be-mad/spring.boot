package by.dma.angular.auth.repositories;

import by.dma.angular.auth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}