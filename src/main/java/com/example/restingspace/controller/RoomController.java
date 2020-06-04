package com.example.restingspace.controller;

import com.example.restingspace.model.Room;
import com.example.restingspace.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void addRoom(Room room, BindingResult result) {

        if (result.hasErrors()) {
            return;
        }
        roomService.addRoom(room);
        MultipartFile image = room.getRoomImage();
        if (image != null && !image.isEmpty()) {
            // Mac
            Path path = Paths.get("/Users/xxx/rooms/" + room.getRid() + ".jpg");

            try {
                image.transferTo(new File(path.toString()));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/delete/{rid}")
    public void deleteRoom(@PathVariable(value = "rid") int rid) {
        // for MAC
        Path path = Paths.get("/Users/xxx/rooms/" + rid + ".jpg");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        roomService.deleteRoom(rid);
    }

    @PostMapping ("/room/updateRoom/{rid}")
    public void updateRoom(@ModelAttribute(value = "updateRoomObj") Room room,
                           @PathVariable(value = "rid") int rid) {
        room.setRid(rid);
        roomService.updateRoom(room);
    }
}