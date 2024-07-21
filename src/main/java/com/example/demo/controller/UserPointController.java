package com.example.demo.controller;

import com.example.demo.model.UserPoint;
import com.example.demo.service.UserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserPointController {

    @Autowired
    private UserPointService  service;


    @PostMapping("/generate")
    @ResponseBody
    public int generateUserPoints () {
        return  service.generate();
    }

    @GetMapping("/userpoint/{userId}")
    @ResponseBody
    public UserPoint getUserPoint (@PathVariable int userId ) {
        return service.getUserPoint(userId);
    }

    @PostMapping("/userpoint/activate/{userId}")
    @ResponseBody
    public UserPoint setActivate (@PathVariable int userId ) {
        return service.setActivate(userId, true );
    }


    @PostMapping("/userpoint/deactivate/{userId}")
    @ResponseBody
    public UserPoint setDeactive (@PathVariable int userId ) {
        return service.setActivate(userId, false );
    }

    @PostMapping ("/userpoint/transfer")
    @ResponseBody
    public UserPoint transfer (@RequestBody TransferForm transferForm ) {
       return service.transfer(transferForm.getFromUserId(), transferForm.getToUserId(), transferForm.getPoint());
    }

}
