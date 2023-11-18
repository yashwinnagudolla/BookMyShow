package com.yashwin.BookMyShow.dto;

import com.yashwin.BookMyShow.models.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignupResponseDTO {
    private Long id;
    private String name;
    private String email;
    private int responseCode;
    private String responseMessage;
    private List<Ticket> tickets;

}
