package com.yashwin.BookMyShow.service;

import com.yashwin.BookMyShow.models.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(Long userId, List<Long> showSeatIds,Long showId);
    Ticket cancelTicket(Long ticketId);
    Ticket transferTicket(Long ticketId,Long fromUserId,Long toUserId);
}
