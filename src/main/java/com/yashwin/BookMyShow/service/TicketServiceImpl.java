package com.yashwin.BookMyShow.service;

import com.yashwin.BookMyShow.exception.ShowSeatNotAvailableException;
import com.yashwin.BookMyShow.exception.TicketNotFoundException;
import com.yashwin.BookMyShow.exception.UserNotFoundException;
import com.yashwin.BookMyShow.models.*;
import com.yashwin.BookMyShow.repository.ShowRepository;
import com.yashwin.BookMyShow.repository.ShowSeatRepository;
import com.yashwin.BookMyShow.repository.TicketRepository;
import com.yashwin.BookMyShow.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService{
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;
    private ShowRepository showRepository;
    Override
    public Ticket bookTicket(Long userId, @NotNull List<Long> showSeatIds, Long showId){
        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();
        for(Long showSeatId: showSeatIds){
            ShowSeat showSeat = showSeatRepository.findById(showSeatId).get();
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            }else{
                throw new ShowSeatNotAvailableException("The selected seats are not available for booking");
            }
            showSeatRepository.save(showSeat);
        }
        boolean paymentDone = paymentCheck();
        List<ShowSeat> showSeats = new ArrayList<>();
        double amount = 0;
        if(paymentDone){
            for(Long showSeatId : showSeatIds){
                ShowSeat showSeat = showSeatRepository.findById(showSeatId).get();
                showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
                showSeatRepository.save(showSeat);
                showSeats.add(showSeat);
                amount += showSeat.getPrice();
            }
        }
        return ticketGenerator(user,showSeats,amount,show);

    }
    @Override
    public Ticket cancelTicket(Long ticketId) {
        Optional<Ticket> ticketOptional= ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket for given Id is not found");
        }
        Ticket ticket = ticketOptional.get();
        ticket.setBookingStatus(BookingStatus.CANCELLED);
        for(ShowSeat showSeat : ticket.getShowSeats()){
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }
        for(Payment payment : ticket.getPayments()){
            payment.getRefNo();
            //Send to the third party for refund
        }
        return ticket;
    }
    public Ticket transferTicket(Long ticketId,Long fromUserId,Long toUserId){
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket for given Id is not found");
        }
        Ticket ticket = ticketOptional.get();
        Optional<User> fromUserOptional = userRepository.findById(fromUserId);
        Optional<User> toUserOptional = userRepository.findById(toUserId);
        if(fromUserOptional.isEmpty() || toUserOptional.isEmpty()){
            throw new UserNotFoundException("User details for the ticket transfer is not found");
        }
        User fromUser = fromUserOptional.get();
        User toUser = toUserOptional.get();

        List<Ticket> bookedTicketHistory = fromUser.getTickets();
        bookedTicketHistory.remove(ticket);
        userRepository.save(fromUser);

        bookedTicketHistory = toUser.getTickets();
        bookedTicketHistory.add(ticket);
        toUser.setTickets(bookedTicketHistory);
        toUser = userRepository.save(toUser);

        ticket.setUser(toUser);
        return ticketRepository.save(ticket);
    }

    private boolean paymentCheck(){
        return true;
    }

    private Ticket ticketGenerator(User user,List<ShowSeat> showSeats,double amount,Show show){
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowSeats(showSeats);
        ticket.setShow(show);
        ticket.setAmount(amount);
        ticket.setBookadAt(LocalDateTime.now());
        ticket.setBookingStatus(BookingStatus.CONFIRMED);
        return ticketRepository.save(ticket);
    }

}
