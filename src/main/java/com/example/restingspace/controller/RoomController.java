package com.example.restingspace.controller;

import com.example.restingspace.model.Room;
import com.example.restingspace.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @RequestMapping(value = "/getAllRooms", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getRoom/{rid}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoom(@PathVariable(value = "rid") int rid) {
        Room room = RoomService.getRoom(rid);
        return new ResponseEntity<>(room, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/room/addRoom", method = RequestMethod.POST)
    public void addRoom(@ModelAttribute(value = "roomForm") Room room, BindingResult result) {

        if (result.hasErrors()) {
            return;
        }
        roomService.addRoom(room);
        MultipartFile image = room.getRoomImage();
        if (image != null && !image.isEmpty()) {
            // Mac
            Path path = Paths.get("/Users/xxx/rooms/" + room.getRid() + ".jpg");

            // windows
//			Path path = Paths.get("C:\\rooms\\" + room.getRid() + ".jpg");
            try {
                image.transferTo(new File(path.toString()));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/admin/delete/{rid}")
    public void deleteRoom(@PathVariable(value = "rid") int rid) {
        // for MAC
        Path path = Paths.get("/Users/xxx/rooms/" + rid + ".jpg");
        // For windows
        //Path path = Paths.get("C:\\rooms\\" + rid + ".jpg");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        roomService.deleteRoom(rid);
    }

    @RequestMapping(value = "/admin/room/updateRoom/{rid}", method = RequestMethod.POST)
    public void updateRoom(@ModelAttribute(value = "updateRoomObj") Room room,
                           @PathVariable(value = "rid") int rid) {
        room.setRid(rid);
        roomService.updateRoom(room);
    }
}
