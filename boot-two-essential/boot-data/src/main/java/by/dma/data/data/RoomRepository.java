package by.dma.data.data;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.data.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
