package com.mainproject.demo.Tripmate.Controller;

import com.mainproject.demo.Tripmate.Entity.Bookings;
import com.mainproject.demo.Tripmate.Entity.Trips;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Service.TripmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin//
@RequestMapping(path = "/tripmate/admin")
public class AdminController {

    @Autowired
    private final TripmateService tripmateService;

    public AdminController(TripmateService tripmateService) {
        this.tripmateService = tripmateService;
    }

    @GetMapping(path = "/users")
    public String getUser(Model model){
        model.addAttribute("user",tripmateService.getAllUsers());
        return "users";
    }

    //    Description: Allows the user to view their profile.
    @GetMapping(path = "/user/{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") int userId){
        return tripmateService.getUserById(userId);
    }

    //    Description: Allows the user to view trips.
    @GetMapping(path = "/trips")
    public List<Trips> getTrips(
            @RequestParam(required = true) LocalDate dateOfTravel,
            @RequestParam(required = true) String departureStation,
            @RequestParam(required = true) String destinationStation){
        return tripmateService.getTrips(dateOfTravel, departureStation,destinationStation);
    }

    //Description: Allows the admin to add available trips.
    @PostMapping(path = "/trips/add")
    public void addNewTrip(@RequestBody Trips trip){
        tripmateService.addTrip(trip);
    }

    //    Description: Allows the admin/ticketing officer to view a trip.
    @GetMapping(path = "/trips/{tripId}")
    public Optional<Trips> getTripById(@PathVariable("tripId") int tripId){
        return tripmateService.getTripById(tripId);
    }

    //    Description: Allows the admin/ticketing officer to view all trips.
    @GetMapping(path = "/trips/all")
    public List<Trips> getAllTrips(){
        return tripmateService.getAllTrips();
    }

    //TODO: Admin should be able to update trips that are available for booking.
//    Description: Allows the admin/ticketing officer to update a trip.
    @PutMapping(path = "/trips/{tripId}/update")
    public String updateTrip(
            @PathVariable("tripId") int tripId,
            @RequestParam(required = false) LocalDate dateOfTravel,
            @RequestParam(required = false) LocalTime timeOfTravel,
            @RequestParam(required = false) String seatType,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String departureStation,
            @RequestParam(required = false) String destinationStation){
        tripmateService.updateTrip(tripId, dateOfTravel, timeOfTravel,seatType,location,departureStation, destinationStation);
        return "Trip updated successfully";
    }
    //    Description: Allows the admin/ticketing officer to delete a trip.
    @DeleteMapping(path = "/trips/{tripId}/delete")
    public void deleteTrip(@PathVariable("tripId") int tripId) {
        tripmateService.deleteTrip(tripId);
    }


    //    Description: Allows and admin and user to cancel a ticket.
    @DeleteMapping(path = "/bookings/{bookingId}/cancel")
    public void cancelTicket(@PathVariable("bookingId") int bookingId){
        tripmateService.cancelTicket(bookingId);
    }

    //    Description: Allows the admin to view all bookings.
    @GetMapping(path = "/bookings/all")
    public List<Bookings> getBookings(){
        return tripmateService.getBookings();
    }


//    Description: Allows the admin/ticketing officer to search for passenger booking information by booking number, Trip Information, etc.
    //revisit
    @GetMapping(path = "/booking/{bookingId}")
    public List<Bookings> adminSearch(@PathVariable() int bookingId){
        return tripmateService.adminSearch(bookingId);
        }
}
