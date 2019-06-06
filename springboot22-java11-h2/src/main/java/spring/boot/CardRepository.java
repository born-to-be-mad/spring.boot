package spring.boot;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.model.Card;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public interface CardRepository extends JpaRepository<Card, Long> {
}
