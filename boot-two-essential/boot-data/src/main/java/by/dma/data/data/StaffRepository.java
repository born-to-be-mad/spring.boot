package by.dma.data.data;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.data.models.StaffMember;

public interface StaffRepository extends JpaRepository<StaffMember, String> {
}
