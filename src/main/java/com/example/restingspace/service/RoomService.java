package com.example.restingspace.service;

import com.example.restingspace.Dao.RoomDao;
import com.example.restingspace.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private static RoomDao roomDao;

    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    public static Room getRoom(long rid) {
        return roomDao.getRoom((int) rid);
    }

    public void deleteRoom(long roomId) {
        roomDao.deleteRoom((int) roomId);
    }

    public void addRoom(Room room){
        roomDao.addRoom(room);
    }

    public void updateRoom(Room room){
        roomDao.updateRoom(room);
    }
}
