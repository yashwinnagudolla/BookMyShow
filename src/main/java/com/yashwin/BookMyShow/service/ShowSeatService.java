package com.yashwin.BookMyShow.service;

import com.yashwin.BookMyShow.models.ShowSeat;
import com.yashwin.BookMyShow.models.ShowSeatStatus;

import java.util.List;

public interface ShowSeatService {
    ShowSeat getShowSeat(Long showSeatId);
    List<ShowSeat> getShowSeats(List<Long> showSeats);
    ShowSeat updateShowSeat(ShowSeat showSeat, ShowSeatStatus showSeatStatus);
    List<ShowSeat> updateShowSeats(List<ShowSeat> showSeats);
}
