package by.dma.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import by.dma.jpa.domain.Department;
import by.dma.jpa.repo.DepartmentRepository;


/**
 * Demonstrate JPA Repository methods with DepartmentRepository.
 */
@DataJpaTest
public class DepartmentJpaRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Exercise JPA Repository methods.
     */
    @Test
    public void runJpaRepositoryMethods() {

        departmentRepository.save(new Department("Humanities"));
        departmentRepository.flush();

        departmentRepository.saveAndFlush(new Department("Fine Arts"));

        departmentRepository.save(new Department("Social Science"));

        System.out.println("\n*************3 Departments*************");
        departmentRepository.findAll().forEach(System.out::println);

        departmentRepository.deleteInBatch(
                departmentRepository.findAll().subList(0,1));

        System.out.println("\n*************1 Less Departments*************");
        departmentRepository.findAll().forEach(System.out::println);
        departmentRepository.deleteAllInBatch();
        System.out.println("\n*************Zero Departments*************");
        departmentRepository.findAll().forEach(System.out::println);

    }

    @BeforeEach
    @AfterEach
    public void banner() {
        System.out.println("\n\n-------------------------------------------------" +
                "-------------------------------------\n");
    }
}
