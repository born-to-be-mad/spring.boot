package by.dma.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROOM")
public class Room {
  @Id
  @Column(name="ROOM_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name="NAME")
  private String roomName;

  @Column(name="ROOM_NUMBER")
  private String roomNumber;

  @Column(name="BED_INFO")
  private String bedInfo;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }

  public String getBedInfo() {
    return bedInfo;
  }

  public void setBedInfo(String bedInfo) {
    this.bedInfo = bedInfo;
  }
}
