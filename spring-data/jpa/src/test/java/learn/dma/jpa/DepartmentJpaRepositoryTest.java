package learn.dma.jpa;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import learn.dma.jpa.domain.Department;
import learn.dma.jpa.domain.Staff;
import learn.dma.jpa.repo.DepartmentRepository;
import learn.dma.jpa.repo.StaffRepository;

import static java.util.stream.Collectors.toList;


/**
 * Demonstrate JPA Repository methods with DepartmentRepository.
 */
@DataJpaTest
public class DepartmentJpaRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Exercise JPA Repository methods.
     */
    @Test
    public void runJpaRepositoryMethods() {

        Department dep1 = departmentRepository.save(
            new Department("Humanities", staffRepository.save(new Staff(null))));
        departmentRepository.flush();

        Department dep2 = departmentRepository.saveAndFlush(
            new Department("Fine Arts", staffRepository.save(new Staff(null))));

        Department dep3 = departmentRepository.save(
            new Department("Social Science", staffRepository.save(new Staff(null))));

        System.out.println("\n*************3 Departments*************");
        departmentRepository.findAll().forEach(System.out::println);

/*        departmentRepository.deleteInBatch(
            departmentRepository.findAll().subList(0, 1));*/

        List<Integer> newlyCreatedDepartmentIds = List.of(dep1, dep2, dep3).stream()
                                                      .map(Department::getId)
                                                      .collect(toList());
        departmentRepository.deleteInBatch(() -> {
            List<Department> departments = departmentRepository.findAllById(newlyCreatedDepartmentIds)
                                                               .subList(0, 1);
            departments.forEach(department -> staffRepository.delete(department.getChair()));
            return departments.listIterator();
        });

        System.out.println("\n*************1 Less Departments*************");
        departmentRepository.findAll()
                            .forEach(System.out::println);

/*        staffRepository.deleteAll();
        departmentRepository.deleteAllInBatch();
        System.out.println("\n*************Zero Departments*************");
        departmentRepository.findAll()
                            .forEach(System.out::println);*/

    }

    @BeforeEach
    @AfterEach
    public void banner() {
        System.out.println("\n\n-------------------------------------------------" +
                           "-------------------------------------\n");
    }
}
