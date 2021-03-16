package by.dma.demo.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.demo.model.Mentee;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
  Mentee getMenteeByName(String name);
}
