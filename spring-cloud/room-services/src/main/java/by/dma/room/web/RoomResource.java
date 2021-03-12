package by.dma.room.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.dma.room.model.Room;
import by.dma.room.persist.RoomRepository;

@RestController
@RequestMapping("/rooms")
public class RoomResource {
  private final RoomRepository repository;

  public RoomResource(RoomRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Iterable<Room> getAllRooms() {
    return this.repository.findAll();
  }

  @GetMapping("/{id}")
  public Room getRoom(@PathVariable("id") long id) {
    return this.repository.findById(id).get();
  }
}
