package learn.dma.datarest.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import learn.dma.datarest.domain.Staff;

/**
 * DataSource Management for the Staff at the University.
 *
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff,Integer> {
}
