package by.dma.crudwebapp.controller.repository;

import by.dma.crudwebapp.controller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This JPA Repository have access to our data in database by using JPA Entity Manager
 * behind the scene to access the data .
 *
 * @author dzmitry.marudau
 * @since 2019.7
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
