package com.mainproject.demo.Tripmate.Service;

import com.mainproject.demo.Tripmate.Entity.Token;
import com.mainproject.demo.Tripmate.Repository.BookingRepository;
import com.mainproject.demo.Tripmate.Entity.Bookings;
import com.mainproject.demo.Tripmate.Entity.Trips;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Repository.TripRepository;
import com.mainproject.demo.Tripmate.Repository.TripmateRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TripmateService{

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final TripmateRepository tripmateRepository;
    private final TripRepository tripRepository;
    private final BookingRepository bookingsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final TokenService tokenService;


    //users can sign up
    public String signUpUser(Users user) {
        //check if username already exists
        boolean userExists = tripmateRepository.
                findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("email already exists");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        tripmateRepository.save(user);
        return "Great";
    }

    //admin can get all users
    public List<Users> getAllUsers() {
        return tripmateRepository.findAll();
    }

    //admin can find user by email
    public Users findByEmail(String email){
        return tripmateRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"))  ;
    }

    //users can get their profile details
    public Optional<Users> getUserById(int userId) {
        boolean userExists = tripmateRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalStateException("User does not exist");
        }
        return tripmateRepository.findById(userId);
    }

    //admin and user can search a trip
    public List<Trips> getTrips(
            LocalDate dateOfTravel,
            String departureStation,
            String destinationStation) {
        List<Trips> trip = tripRepository.findByDateOfTravelAndDepartureStationAndDestinationStation(dateOfTravel, departureStation, destinationStation);
        if (trip.isEmpty()) {
            throw new IllegalStateException("No trips found,Please select another date");
        };
         return tripRepository.findByDateOfTravelAndDepartureStationAndDestinationStation(dateOfTravel, departureStation, destinationStation);
    }

    //admin can add trips
    public void addTrip(Trips trip) {
        // check if trip already exists
        boolean tripExists = tripRepository.existsById(trip.getTripId());
        if (tripExists) {
            throw new IllegalStateException("Trip already exists");
        }
        tripRepository.save(trip);
    }

    //admin can find trips by trip id
    public Optional<Trips> getTripById(int tripId) {
        boolean tripExists = tripRepository.existsById(tripId);
        if (!tripExists) {
            throw new IllegalStateException("Trip doesn't exist");
        }
        return tripRepository.findById(tripId);
    }

    public List<Trips> getAllTrips() {
        return tripRepository.findAll();
    }

    //TODO
    //admin can update trips
    public String updateTrip(int tripId,
                                  LocalDate dateOfTravel,
                                  LocalTime timeOfTravel,
                                  String seatType,
                                  String location,
                                  String departureStation,
                                  String destinationStation) {
        Trips trips = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalStateException(
                        "trip with id " + tripId + " does not exist"
                ));
        if (dateOfTravel != null &&
                dateOfTravel.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Date of travel cannot be in the past");
        }
        if (dateOfTravel != null &&//
                !Objects.equals(trips.getDateOfTravel(), dateOfTravel)) {
            trips.setDateOfTravel(dateOfTravel);
        }

        if (timeOfTravel != null &&
                !Objects.equals(trips.getTimeOfTravel(), timeOfTravel)) {
            trips.setTimeOfTravel(timeOfTravel);
        }
        if (seatType != null &&
                !Objects.equals(trips.getSeatType(), seatType)) {
            trips.setSeatType(seatType);
        }
        if (location != null &&
                !Objects.equals(trips.getLocation(), location)) {
            trips.setLocation(location);
        }
        if (departureStation != null &&
                !Objects.equals(trips.getDepartureStation(), departureStation)) {
            trips.setDepartureStation(departureStation);
        }
        if (destinationStation != null &&
                !Objects.equals(trips.getDestinationStation(), destinationStation)) {
            trips.setDestinationStation(destinationStation);
        }
        return "Trip updated successfully";
    }

    public void deleteTrip(int tripId) {
        boolean tripExists = tripRepository.existsById(tripId);
        if (!tripExists) {
            throw new IllegalStateException("Trip does not exist");
        }
        tripRepository.deleteById(tripId);
    }

    //TODO
    //users can book a ticket
    public String bookTicket(Trips trip,int userId) {
        //TODO: Add user_id as controller argument;
        //
        Users users = tripmateRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exist"
                ));
        String Status = "Pending";
        Bookings booking = new Bookings(
                users,
                trip,
                Status
        );
        bookingsRepository.save(booking);
        return "Ticket booked successfully";
    }

    //users can cancel a ticket
        public void cancelTicket(int bookingId) {
        // check if booking exists
        boolean bookingExists = bookingsRepository.existsById(bookingId);
        if (!bookingExists) {
            throw new IllegalStateException("Booking does not exist");
        }
            bookingsRepository.deleteBybookingId(bookingId);
    }

    //admin can view all bookings
    public List<Bookings> getBookings() {
        return bookingsRepository.findAll();
    }

    //user can view all bookings by user id
    public List<Bookings> getBookingsByUserId(Users userId) {
        return bookingsRepository.findByUser(userId);
    }

    //TODO
    public List<Bookings> adminSearch(int bookingId) {
        boolean bookingExists = bookingsRepository.existsById(bookingId);
        if (!bookingExists) {
            throw new IllegalStateException("Booking does not exist");
        }
        return bookingsRepository.findBybookingId(bookingId);
    }
}
