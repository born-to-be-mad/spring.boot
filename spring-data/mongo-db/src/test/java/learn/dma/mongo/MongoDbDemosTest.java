package learn.dma.mongo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import learn.dma.mongo.domain.Staff;
import learn.dma.mongo.repo.DepartmentRepository;
import learn.dma.mongo.repo.StaffRepository;

/**
 * Demonstrate Various Querying Techniques with Spring Data MongoDb
 */
@DataMongoTest
public class MongoDbDemosTest {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Queries to Mongo DB.
     * <p>
     * Staff and Departments persisted to Fongo in-Memory database at startup.
     *
     * @see MongoDbApplication
     */
    @Test
    public void mongoQueryMethods() {

        //***************Staff query methods***************

        //Paging and Sorting Queries
        System.out.println("\nFind all staff members, sort alphabetically by last name");
        Sort sortByLastName = Sort.by(Sort.Direction.ASC, "member.lastName");
        staffRepository.findAll(sortByLastName).forEach(System.out::println);

        System.out.println("\nFind first 5 Staff members, sort alphabetically by last name");
        Page<Staff> members = staffRepository.findAll(PageRequest.of(0, 5, sortByLastName));
        members.forEach(System.out::println);


        //Property Expression
        System.out.println("\nFind all staff members with last name King");
        staffRepository.findByMemberLastName("King").forEach(System.out::println);

        //@Query with JSON query string
        //"{ 'member.firstName' : ?0 }"
        System.out.println("\nFind all staff members with first name John");
        staffRepository.findByFirstName("John").forEach(System.out::println);


        //***************Department query methods***************

        //Sorting example, MongoRepository extends PagingAndSortingRepository
        System.out.println("\nFind all Departments, sort alphabetically by  name");
        departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                            .forEach(System.out::println);

        //Property Expression
        System.out.println("\nFind Department with the exact name 'Humanities' \n" +
                           departmentRepository.findByName("Humanities"));

        //@Query with JSON query string that accepts regular expression as a parameter
        //{ 'name' : { $regex: ?0 } }
        //Any department name that ends in sciences where 's' is case insensitive
        System.out.println("\nFind all Departments with name ending in Sciences");
        departmentRepository.findNameByPattern(".[Ss]ciences").forEach(System.out::println);


        //Invalid Method, will fail at runtime
/*        System.out.println("\nInvalid Method, cannot cross DBRef's in queries");
        departmentRepository.findByChairMemberLastName("Jones");*/
    }

    @BeforeEach
    @AfterEach
    public void printBanner() {
        System.out.println("*************************************************************************************");
    }

}
