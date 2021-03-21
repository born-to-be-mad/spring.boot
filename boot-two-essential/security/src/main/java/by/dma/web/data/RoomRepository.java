package by.dma.web.data;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.web.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
