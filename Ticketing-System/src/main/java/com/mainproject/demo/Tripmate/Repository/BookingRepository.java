package com.mainproject.demo.Tripmate.Repository;

import com.mainproject.demo.Tripmate.Entity.Bookings;
import com.mainproject.demo.Tripmate.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository
        extends JpaRepository<Bookings, Integer> {
    boolean existsById(int bookingId);

    @Transactional
    @Modifying
    void deleteBybookingId(int bookingId);

    List<Bookings> findByUser(Users user);

    List<Bookings> findBybookingId(int bookingId);

    // List<Trips> findByTripIdAndSeatType(int tripId, String seatType);
    // boolean existsByStatus(String Status);
}
