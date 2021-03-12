package by.dma.reservation.client;

import java.sql.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import by.dma.reservation.dto.Reservation;

@FeignClient("reservationservices")
public interface ReservationClient {

  @GetMapping("/reservations")
  List<Reservation> getAllReservations(@RequestParam(name = "date", required = false) Date date);

  @GetMapping("/reservations/{id}")
  Reservation getReservation(@PathVariable("id") long id);
}
