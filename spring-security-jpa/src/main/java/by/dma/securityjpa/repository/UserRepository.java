package by.dma.securityjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.securityjpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserName(String userName);

}
