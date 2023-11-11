package com.yashwin.BookMyShow.models;

import jakarta.persistence.ManyToOne;

public class Seat extends BaseModel{
    private int rowVal;
    private int colVal;
    private String seatNumber;
    @ManyToOne
    private SeatType seatType;

}
