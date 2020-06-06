package com.example.restingspace.controller;

import com.example.restingspace.model.Room;
import com.example.restingspace.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping ("/getAllRooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getRoom/{rid}")
    public ResponseEntity<Room> getRoom(@PathVariable(value = "rid") int rid) {
        Room room = roomService.getRoom(rid);
        return new ResponseEntity<>(room, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/room/addRoom")
    public ResponseEntity<Object> addRoom(@RequestBody Room room) {
        Integer rid = roomService.getAllRooms().size() + 1;
        room.setRid(rid);

        roomService.addRoom(room);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(room.getRid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/room/{rid}")
    public void deleteRoom(@PathVariable(value = "rid") int rid) {
        roomService.deleteRoom(rid);
    }

    //***
    @PostMapping ("/room/updateRoom/{rid}")
    public void updateRoom(@ModelAttribute(value = "updateRoomObj") Room room,
                           @PathVariable(value = "rid") int rid) {
        room.setRid(rid);
        roomService.updateRoom(room);
    }
}
