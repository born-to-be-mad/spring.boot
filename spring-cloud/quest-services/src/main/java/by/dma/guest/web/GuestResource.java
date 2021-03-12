package by.dma.guest.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.dma.guest.model.Guest;
import by.dma.guest.persistent.GuestRepository;


@RestController
@RequestMapping("/guests")
public class GuestResource {
  private final GuestRepository repository;

  public GuestResource(GuestRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Iterable<Guest> getAllGuests() {
    return this.repository.findAll();
  }

  @GetMapping("/{id}")
  public Guest getGuest(@PathVariable("id") long id) {
    return repository.findById(id).get();
  }
}
