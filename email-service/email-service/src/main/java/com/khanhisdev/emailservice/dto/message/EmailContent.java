package com.khanhisdev.emailservice.dto.message;

import lombok.Data;

import java.util.List;
@Data
public class EmailContent {
    private String desEmail;
    private String nameMovie;
    private String nameTheater;
    private String nameRoom;
    private String showtime;
    private List<String> seats;
}