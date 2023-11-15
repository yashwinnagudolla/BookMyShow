package com.yashwin.BookMyShow.service;

import com.yashwin.BookMyShow.exception.ShowSeatNotAvailableException;
import com.yashwin.BookMyShow.models.ShowSeat;
import com.yashwin.BookMyShow.models.ShowSeatStatus;
import com.yashwin.BookMyShow.repository.ShowSeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowSeatServiceImpl implements ShowSeatService{
    private ShowSeatRepository showSeatRepository;
    public ShowSeat getShowSeat(Long showSeatId){
        Optional<ShowSeat> showSeatOptional = showSeatRepository.findById(showSeatId);
        if (showSeatOptional.isEmpty()){
            throw new ShowSeatNotAvailableException("Show seats is not availabe for the given showseat Id");
        }
        return showSeatOptional.get();
    }
    public List<ShowSeat> getShowSeats(List<Long> showSeats){
        List<ShowSeat> showSeatList = new ArrayList<>();
        for(Long showSeat : showSeats){
            Optional<ShowSeat> showSeatOptional = showSeatRepository.findById(showSeat);
            if (showSeatOptional.isEmpty()){
                throw new ShowSeatNotAvailableException("Show seats is not availabe for the given showseat Id");
            }
            showSeatList.add(showSeatOptional.get());
        }
        return showSeatList;

    }
    public ShowSeat updateShowSeat(ShowSeat showSeat, ShowSeatStatus showSeatStatus){
        showSeat.setShowSeatStatus(showSeatStatus);
        return showSeatRepository.save(showSeat);
    }
    public List<ShowSeat> updateShowSeats(List<ShowSeat> showSeats){
        List<ShowSeat> showSeatList = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
            showSeatList.add(showSeat);
        }
        return showSeatList;

    }
}
