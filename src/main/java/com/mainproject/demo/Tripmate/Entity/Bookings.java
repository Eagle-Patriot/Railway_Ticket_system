package com.mainproject.demo.Tripmate.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "userId"
    )
    private Users user;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "tripId"
    )
    private Trips trips;
    private String Status = "Pending";

    public Bookings(Users user,
                    Trips trips,
                    String Status) {
        this.user = user;
        this.trips = trips;
        this.Status = Status;
    }



    public Object getSeatType() {
        return trips.getSeatType();
    }
}