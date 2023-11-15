package com.yashwin.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int rowVal;
    private int colVal;
    private String seatNumber;
    @ManyToOne
    private SeatType seatType;

}
