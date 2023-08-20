package com.mainproject.demo.Tripmate.Repository;

import com.mainproject.demo.Tripmate.Entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository
        extends JpaRepository<Trips, Integer> {
    boolean existsById(int tripId);

    @Transactional
    @Modifying
    List<Trips> findByDateOfTravelAndDepartureStationAndDestinationStation(LocalDate dateOfTravel, String departureStation, String destinationStation);

    List<Trips> findByTripIdAndSeatType(int tripId, String seatType);
}
