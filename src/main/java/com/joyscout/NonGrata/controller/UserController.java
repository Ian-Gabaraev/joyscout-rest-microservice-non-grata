package com.joyscout.NonGrata.controller;

import com.joyscout.NonGrata.model.User;
import com.joyscout.NonGrata.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String deleteOrRetrievePath = "/banlist/{id}";
    private static final String createPath = "/banlist";

    @Autowired
    UserService userService;

    @GetMapping(deleteOrRetrievePath)
    public ResponseEntity<?> findById(@PathVariable("id") final String id) {

        if (userService.isBanned(id)) {
            logger.info("User is banned");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            logger.info("User is not banned");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(createPath)
    public ResponseEntity<?> ban(@RequestBody final User user) {
        userService.ban(user);
        logger.info("User banned successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(deleteOrRetrievePath)
    public ResponseEntity<?> unban(@PathVariable("id") final String id) {
        if (! userService.isBanned(id)){
            logger.info("User by this ID not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            userService.unban(id);
            logger.info("User unbanned");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
