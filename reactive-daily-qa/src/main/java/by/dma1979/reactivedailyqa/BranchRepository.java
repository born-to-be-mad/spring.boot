package by.dma1979.reactivedailyqa;

import by.dma1979.reactivedailyqa.model.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
public interface BranchRepository extends ReactiveCrudRepository<Branch, String> {
}
