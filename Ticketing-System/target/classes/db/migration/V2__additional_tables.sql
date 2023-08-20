CREATE TABLE seats (
    seat_id SERIAL PRIMARY KEY,
    trip_id INTEGER REFERENCES trips(trip_id),
    seat_number VARCHAR(10) NOT NULL,
    seat_type VARCHAR(20) NOT NULL,
    is_available BOOLEAN NOT NULL
);

CREATE TABLE passenger_information (
    passenger_id SERIAL PRIMARY KEY,
    booking_id INTEGER REFERENCES bookings(booking_id),
    seat_id INTEGER REFERENCES seats(seat_id),
    passenger_name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);


