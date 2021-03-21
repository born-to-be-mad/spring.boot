package by.dma.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.dma.data.data.RoomRepository;
import by.dma.data.models.Room;

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
