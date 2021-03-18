package learn.dma.mongo.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import learn.dma.mongo.domain.Staff;

/**
 * DataSource Management for the Staff at the University.
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {

    List<Staff> findByMemberLastName(String lastName);

    @Query("{ 'member.firstName' : ?0 }")
    List<Staff> findByFirstName(String firstName);

}
