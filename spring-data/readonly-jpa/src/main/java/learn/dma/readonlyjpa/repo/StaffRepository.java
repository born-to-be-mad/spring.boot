package learn.dma.readonlyjpa.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import learn.dma.readonlyjpa.domain.Staff;

/**
 * DataSource Management for the Staff at the University.
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {
}
