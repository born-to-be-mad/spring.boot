package by.dma.reservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import by.dma.reservation.dto.Guest;

@FeignClient("guestservices")
public interface GuestClient {

  @GetMapping("/guests")
  List<Guest> getAllGuests();

  @GetMapping("/guests/{id}")
  Guest getGuest(@PathVariable("id") long id);
}
