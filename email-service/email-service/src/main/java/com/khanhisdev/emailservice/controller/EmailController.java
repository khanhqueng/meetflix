package com.khanhisdev.emailservice.controller;

import com.khanhisdev.emailservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailSenderService emailSenderService;
    @PostMapping()
    public ResponseEntity<String> sendSeatsInfoToEmail(@RequestParam(name = "to") String email,
                                                       @RequestParam(name = "seats") List<String> seats){
        String info= String.join(",",seats);
        Map<String, Object> map =Map.of(
                "seat", info
        );
        emailSenderService.sendSeatsInformation(email,map);
        return new ResponseEntity<>("Send success", HttpStatus.OK);
    }
}
