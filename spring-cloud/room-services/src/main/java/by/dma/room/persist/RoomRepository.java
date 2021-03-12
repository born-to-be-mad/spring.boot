package by.dma.room.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.dma.room.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
