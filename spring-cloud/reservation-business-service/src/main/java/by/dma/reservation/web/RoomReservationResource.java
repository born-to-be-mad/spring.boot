package by.dma.reservation.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.dma.reservation.client.GuestClient;
import by.dma.reservation.client.ReservationClient;
import by.dma.reservation.client.RoomClient;
import by.dma.reservation.dto.Guest;
import by.dma.reservation.dto.Reservation;
import by.dma.reservation.dto.Room;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationResource {
  private final RoomClient roomClient;
  private final GuestClient guestClient;
  private final ReservationClient reservationClient;

  private static final DateFormat DATE_FORMAT =
          new SimpleDateFormat("yyyy-MM-dd");

  public RoomReservationResource(RoomClient roomClient,
                                 GuestClient guestClient,
                                 ReservationClient reservationClient) {
    super();
    this.roomClient = roomClient;
    this.guestClient = guestClient;
    this.reservationClient = reservationClient;
  }

  @GetMapping
  public List<by.dma.reservation.RoomReservation> getRoomReservations(
          @RequestParam(name = "date", required = false) String dateString) {
    Date date = createDateFromDateString(dateString);
    List<Room> rooms = this.roomClient.getAllRooms();
    Map<Long, by.dma.reservation.RoomReservation> roomReservations =
            new HashMap<>();
    rooms.forEach(room -> {
      by.dma.reservation.RoomReservation roomReservation =
              new by.dma.reservation.RoomReservation();
      roomReservation.setRoomId(room.getId());
      roomReservation.setRoomName(room.getName());
      roomReservation.setRoomNumber(room.getRoomNumber());
      roomReservations.put(room.getId(), roomReservation);
    });
    List<Reservation> reservations =
            this.reservationClient.getAllReservations(new java.sql.Date(date.getTime()));
    reservations.forEach(reservation -> {
      by.dma.reservation.RoomReservation roomReservation =
              roomReservations.get(reservation.getRoomId());
      roomReservation.setDate(date);
      Guest guest = this.guestClient.getGuest(reservation.getGuestId());
      roomReservation.setGuestId(guest.getId());
      roomReservation.setFirstName(guest.getFirstName());
      roomReservation.setLastName(guest.getLastName());
    });

    return new ArrayList<>(roomReservations.values());
  }


  private Date createDateFromDateString(String dateString) {
    Date date;
    if (null != dateString) {
      try {
        date = DATE_FORMAT.parse(dateString);
      } catch (ParseException pe) {
        date = new Date();
      }
    } else {
      date = new Date();
    }
    return date;
  }

}
