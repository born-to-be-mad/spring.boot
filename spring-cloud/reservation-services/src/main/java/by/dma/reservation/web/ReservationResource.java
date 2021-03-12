package by.dma.reservation.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.dma.reservation.model.Reservation;
import by.dma.reservation.persist.ReservationRepository;

@RestController
@RequestMapping("/reservations")
public class ReservationResource {
  private final ReservationRepository repository;

  public ReservationResource(ReservationRepository repository){
    this.repository = repository;
  }

  @GetMapping
  public Iterable<Reservation> getAllReservations(){
    return this.repository.findAll();
  }

  @GetMapping("/{id}")
  public Reservation getReservation(@PathVariable("id") long id){
    return this.repository.findById(id).get();
  }
}
