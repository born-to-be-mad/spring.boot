package by.dma.reservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import by.dma.reservation.dto.Room;

@FeignClient("roomservices")
public interface RoomClient {

  @GetMapping("/rooms")
  List<Room> getAllRooms();

  @GetMapping("/rooms/{id}")
  Room getRoom(@PathVariable("id") long id);

}
