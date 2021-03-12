package by.dma.reservation.dto;

import java.sql.Date;

public class Reservation {
  private long id;
  private long roomId;
  private long guestId;
  private Date date;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRoomId() {
    return roomId;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public long getGuestId() {
    return guestId;
  }

  public void setGuestId(long guestId) {
    this.guestId = guestId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
