package com.mainproject.demo.Tripmate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Seats {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int seatId;
    private int tripId;
    private String seatNumber;
    private String seatType;
    private boolean isAvailable;

    public Seats(int tripId,
                 String seatNumber,
                 String seatType,
                 boolean isAvailable) {
        this.tripId = tripId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.isAvailable = isAvailable;
    }
}
