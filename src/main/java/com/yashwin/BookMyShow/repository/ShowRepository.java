package com.yashwin.BookMyShow.repository;

import com.yashwin.BookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findById(Long showId);
    Show save(Show show);
}
