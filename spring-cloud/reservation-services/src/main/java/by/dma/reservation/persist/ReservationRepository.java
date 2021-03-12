package by.dma.reservation.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.dma.reservation.model.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
