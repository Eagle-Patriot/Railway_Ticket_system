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
<img width="774" alt="Screenshot 2023-08-20 at 2 08 04 AM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/9bfd54ac-3536-4057-95ef-6eb5c07436e9">

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



## User Registration Page
<img width="714" alt="Screenshot 2023-08-20 at 11 25 40 PM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/bbed711a-0f60-4e5b-9dc5-980f8d307164">


## Email Verification 
<img width="1061" alt="Screenshot 2023-08-20 at 11 28 33 PM" src="https://github.com/Eagle-Patriot/Railway_Ticket_system/assets/114234688/7a8dacbc-3130-485c-91ec-ef3f834111da">


