package com.yashwin.BookMyShow.repository;

import com.yashwin.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    Optional<ShowSeat> findById(Long showSeatId);
    ShowSeat save(ShowSeat showSeat);
}
