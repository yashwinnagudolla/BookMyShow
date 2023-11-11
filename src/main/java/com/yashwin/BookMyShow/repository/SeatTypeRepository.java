package com.yashwin.BookMyShow.repository;

import com.yashwin.BookMyShow.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
    Optional<SeatType> findById(Long showId);
    SeatType save(SeatType seatType);
}
