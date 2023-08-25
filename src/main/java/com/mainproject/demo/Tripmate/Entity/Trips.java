package com.mainproject.demo.Tripmate.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

//this is the entity class for trips table
//it stores the details of the trips
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trips")
public class Trips {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int tripId;
    @Column(name = "date_of_travel")
    private LocalDate dateOfTravel;
    @Column(name = "time_of_travel")
    private LocalTime timeOfTravel;
    @Column(name = "seat_type")
    private String seatType;
    @Column(name = "location")
    private String location;
@Column(name = "departure_station")
    private String departureStation;
    @Column(name = "destination_station")
    private String destinationStation;

    public Trips(LocalDate dateOfTravel,
                 LocalTime timeOfTravel,
                 String seatType,
                 String location,
                 String departureStation,
                 String destinationStation) {
        this.dateOfTravel = dateOfTravel;
        this.timeOfTravel = timeOfTravel;
        this.seatType = seatType;
        this.location = location;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
    }
}
