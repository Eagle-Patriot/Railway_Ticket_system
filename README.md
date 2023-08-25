# Railway_Ticket_system
Welcome to the Railway Reservation/Ticketing System GitHub repository! This application provides users with the ability to book and manage railway tickets, while admins and ticketing officers can manage trips, bookings, and more.
## Overview

The Railway Reservation/Ticketing System is designed to streamline the process of booking and managing railway tickets. The application provides separate functionalities for administrators, ticketing officers, and regular users.

## Getting Started

### Prerequisites
Java 8 or later
Spring Boot
Spring Security
PostgreSQL Database
Thymeleaf
HTML
CSS
Javascript
Bootstrap

### Installation

1. Clone the repository:
   
git clone https://github.com/your-username/Railway_Ticket_System.git
Configure your PostgreSQL database settings in src/main/resources/application.properties.

2. Build and run the application:
   
./mvnw spring-boot:run
The application will be accessible at http://localhost:8080.


## Usage
Users can view trips, book tickets, and manage their bookings.
Admins and ticketing officers can manage trips, view bookings, search for passenger information, and more.

## Entity Relationship Diagram (ERD)
<img width="1680" alt="Screenshot 2023-08-25 at 5 13 48 AM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/3e2b036b-d331-48e5-85f3-3d8ab4ba28b7">


### Users Table
The users table stores information about users, including their first name, last name, email, password, role, and account status.
The user_id serves as the primary key, uniquely identifying each user.
This table relates to bookings via the user_id foreign key.

### Trips Table
The trips table holds details about trips, such as the date and time of travel, seat type, location, departure terminal, and city/station.
The trip_id is the primary key for this table, providing a unique identifier for each trip.
This table is related to bookings and seats using the trip_id foreign key.

### Bookings Table
The bookings table records booking information, including the associated user and trip, as well as the booking status.
The booking_id is the primary key for this table.
The user_id and trip_id fields reference the users and trips tables respectively, creating a link between bookings, users, and trips.

### Seats Table
The seats table stores information about available seats for each trip, including seat number, seat type, and availability status.
The seat_id is the primary key, and the trip_id field references the trips table, establishing a connection between seats and trips.

### Token Table
The token table stores tokens associated with user accounts, which are used for verification and authentication purposes.
Each token is linked to a user through the user_id foreign key.
The token_id is the primary key, and expires_at is used to manage token validity.

### Screenshots
## User Login page
<img width="1680" alt="Screenshot 2023-08-25 at 5 21 08 AM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/0ec81dde-91c5-4bb1-afc4-b8d5a4c71d76">


## User Registration Page
<img width="1680" alt="Screenshot 2023-08-25 at 5 21 12 AM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/db3d261e-d3bc-4175-a23d-dbbaa34c067b">

## Email Verification 
<img width="1680" alt="Screenshot 2023-08-25 at 5 20 41 AM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/fc08b187-8f1d-4f4d-b6dc-433f8586709b">

