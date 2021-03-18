package learn.dma.jpa.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import learn.dma.jpa.domain.Staff;

/**
 * DataSource Management for the Staff at the University.
 * <p>
 * Created by maryellenbowman
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {
}
