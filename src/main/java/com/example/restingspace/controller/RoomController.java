package com.example.restingspace.controller;

import com.example.restingspace.model.Room;
import com.example.restingspace.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ModelAndView("roomList", "rooms", rooms);
    }

    @RequestMapping(value = "/getRoom/{rid}", method = RequestMethod.GET)
    public ModelAndView getRoom(@PathVariable(value = "rid") int rid) {
        Room room = RoomService.getRoom(rid);
        return new ModelAndView("roomPage", "room", room);
    }

    @RequestMapping(value = "/admin/room/addRoom", method = RequestMethod.GET)
    public ModelAndView getRoomForm() {
        return new ModelAndView("addRoom", "roomForm", new Room());
    }

    @RequestMapping(value = "/admin/room/addRoom", method = RequestMethod.POST)
    public String addRoom(@ModelAttribute(value = "roomForm") Room room, BindingResult result) {

        if (result.hasErrors()) {
            return "addRoom";
        }
        roomService.addRoom(room);
        MultipartFile image = room.getRoomImage();
        if (image != null && !image.isEmpty()) {
            // Mac
            Path path = Paths.get("/Users/xxx/rooms/" + room.getRid() + ".jpg");

            // windows
//			Path path = Paths.get("C:\\products\\" + product.getId() + ".jpg");
            try {
                image.transferTo(new File(path.toString()));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/getAllRooms";
    }

    @RequestMapping(value = "/admin/delete/{rid}")
    public String deleteRoom(@PathVariable(value = "rid") int rid) {

        // for MAC
        Path path = Paths.get("/Users/xxx/rooms/" + rid + ".jpg");

        // For windows
        //Path path = Paths.get("C:\\products\\" + productId + ".jpg");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        roomService.deleteRoom(rid);
        return "redirect:/getAllRooms";
    }

    @RequestMapping(value = "/admin/room/updateRoom/{rid}", method = RequestMethod.GET)
    public ModelAndView getEditForm(@PathVariable(value = "rid") int rid) {
        Room room = RoomService.getRoom(rid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateRoom");
        modelAndView.addObject("updateRoomObj", room);
        modelAndView.addObject("rid", rid);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/room/updateRoom/{rid}", method = RequestMethod.POST)
    public String updateRoom(@ModelAttribute(value = "updateRoomObj") Room room,
                           @PathVariable(value = "rid") int rid) {
        room.setRid(rid);
        roomService.updateRoom(room);
        return "redirect:/getAllRooms";
    }
}
