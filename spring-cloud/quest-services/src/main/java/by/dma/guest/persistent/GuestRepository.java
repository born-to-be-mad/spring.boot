package by.dma.guest.persistent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.dma.guest.model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
