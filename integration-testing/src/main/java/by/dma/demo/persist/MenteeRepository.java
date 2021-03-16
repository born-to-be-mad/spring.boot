package by.dma.demo.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import by.dma.demo.model.Mentee;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee getMenteeByName(String name);

    @Query("select avg (grade) from Mentee where active=true")
    Double getAvgGradeForActiveMentees();
}
