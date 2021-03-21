package by.dma.web.data;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.web.models.StaffMember;

public interface StaffRepository extends JpaRepository<StaffMember, String> {
}
