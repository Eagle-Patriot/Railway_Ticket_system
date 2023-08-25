CREATE TABLE users(
user_id serial primary key,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL,
password VARCHAR(20) NOT NULL,
role VARCHAR(20) NOT NULL,
locked boolean default false,
enabled boolean default true
);

CREATE TABLE trips(
trip_id serial primary key,
date_of_travel DATE NOT NULL,
time_of_travel TIME NOT NULL,
seat_type VARCHAR(20) NOT NULL,
location VARCHAR(100) NOT NULL,
departure_terminal VARCHAR(100) NOT NULL,
city_station VARCHAR(100) NOT NULL
);

create table bookings(
booking_id serial primary key,
user_id integer references users(user_id),
trip_id integer references trips(trip_id),
status varchar(20) not null
);
