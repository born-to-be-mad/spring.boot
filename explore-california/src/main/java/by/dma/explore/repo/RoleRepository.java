package by.dma.explore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import by.dma.explore.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
