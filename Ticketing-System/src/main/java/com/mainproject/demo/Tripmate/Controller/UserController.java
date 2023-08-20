package com.mainproject.demo.Tripmate.Controller;

import com.mainproject.demo.Tripmate.Entity.Bookings;
import com.mainproject.demo.Tripmate.Entity.Trips;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Service.TripmateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/tripmate/users")
@AllArgsConstructor
public class UserController {
    private final TripmateService tripmateService;


    //    Description: Allows the user to view trips.
    @GetMapping(path = "/trips")
    public List<Trips> getTrips(
            @RequestParam(required = true) LocalDate dateOfTravel,
            @RequestParam(required = true) String departureStation,
            @RequestParam(required = true) String destinationStation){
        return tripmateService.getTrips(dateOfTravel, departureStation,destinationStation);
    }

    //Description: Allows the user to book a trip.
    @PostMapping(path = "/trips/{userId}/book")
    public String bookTicket(@RequestBody Trips trip, @PathVariable("userId") int userId){
        tripmateService.bookTicket(trip,userId);
        return "Ticket booked successfully";
    }

    //    Description: Allows the users to view all their bookings.
    @GetMapping(path = "/bookings/{userId}")
    public List<Bookings> getBookingsByUserId(@PathVariable("userId") Users userId){
        return tripmateService.getBookingsByUserId(userId);
    }

    //    Description: Allows user and admin and user to cancel a ticket.
    @DeleteMapping(path = "/bookings/{bookingId}/cancel")
    public void cancelTicket(@PathVariable("bookingId") int bookingId){
        tripmateService.cancelTicket(bookingId);
    }
}
