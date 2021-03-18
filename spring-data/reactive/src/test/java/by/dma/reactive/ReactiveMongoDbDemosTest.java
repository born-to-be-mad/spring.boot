package by.dma.reactive;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.reactive.domain.Department;
import by.dma.reactive.domain.Person;
import by.dma.reactive.domain.Staff;
import by.dma.reactive.repo.DepartmentRepository;
import by.dma.reactive.repo.StaffRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * Demonstrate Various Querying Techniques with Spring Data MongoDb.
 */
@DataMongoTest
public class ReactiveMongoDbDemosTest {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Queries to Mongo DB.
     */
    @Test
    public void mongoQueryMethods() {
        //Create 2 Mono Staff publishers, data not persisted yet!
        Mono<Staff> deanJonesMono = staffRepository.save(new Staff(1, new Person("John", "Jones")));
        Mono<Staff> deanMartinMono = staffRepository.save(new Staff(2, new Person("John", "Martin")));
        Mono<Staff> jamesBondMono = staffRepository.save(new Staff(3, new Person("James", "Bond")));

        Flux<Staff> staffFlux = staffRepository.findAll();

        System.out.println("Staff count found in DB BEFORE subscription: " + staffFlux.count().block());

        //Subscribe with block(), returns entity
        Staff deanJones = deanJonesMono.block();
        Staff jamesBond = jamesBondMono.block();

        //Subscribe with subscribe(), does not return entity
        deanMartinMono.subscribe();

        Long countStaff = staffFlux.count().block();
        System.out.println("Staff count found in DB AFTER subscription:" + countStaff);
        Assertions.assertEquals(3, countStaff);

        //Query returns a Flux publisher
        Flux<Staff> deanMartinFindByFlux = staffRepository.findByMemberLastName("Martin");
        Staff deanMartin = deanMartinFindByFlux.blockFirst();

        //DEPARTMENTS

        //Create an asynchronous publisher that gets the total number of Departments
        Mono<Long> departmentCountMono = departmentRepository.findAll().count();

        //Create a asynchronous Flux publisher that persists 3 new Departments
        Flux<Department> departmentFlux = departmentRepository.saveAll(
            Arrays.asList(new Department(100, "Humanities", deanJones),
                          new Department(200, "Natural Sciences", deanMartin),
                          new Department(300, "Social Sciences", jamesBond)));

        System.out.println("Departments found in DB BEFORE subscription: "
                           + departmentCountMono.block());

        //Persist the 3 departments, and block until complete
        departmentFlux.blockLast();

/*        Long countDepartments = departmentCountMono.block();
        System.out.println(("Departments found in DB AFTER subscription: "
                            + countDepartments));
        Assertions.assertEquals(3, countDepartments);*/
    }

    @BeforeEach
    @AfterEach
    public void printBanner() {
        System.out.println("*".repeat(30));
    }

}
