package by.dma.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.dma.web.data.RoomRepository;
import by.dma.web.models.Room;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}
