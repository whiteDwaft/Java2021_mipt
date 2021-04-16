CREATE TABLE aircrafts
(
  aircraft_code CHAR PRIMARY KEY,
  model VARCHAR(100),
  range INT(5)
)
 AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\aircrafts_data.csv');
--  todo добавить foreign key

CREATE TABLE airports
(
  airport_code varchar(3) primary key,
  airport_name varchar(200),
  city         varchar(200),
  coordinates varchar(200) ,
  timezone     varchar(200)
) AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\airports_data.csv');
--  todo добавить foreign key

-- CREATE TABLE boarding_passes (
--  ticket_no   char(13) primary key,
--  flight_id  integer primary key,
--  boarding_no integer,
--  seat_no     varchar(4)
-- )AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\boarding_passes.csv');
-- --  todo добавить foreign key
--
-- CREATE TABLE bookings (
--  book_ref     char(6) primary key,
--  book_date   timestamp,
--  total_amount numeric(10,2)
-- ) AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\bookings.csv');
-- --  todo добавить аттрибуты и foreign key
--
CREATE TABLE flights(
 flight_id        varchar(20) not null primary key,
 flight_no            char(6) not null ,
 scheduled_departure  varchar(100) not null ,
 scheduled_arrival    varchar(100) not null ,
 departure_airport    char(3) not null ,
 arrival_airport      char(3) not null,
 status               varchar(20) not null,
 aircraft_code        char(3)   not null,
 actual_departure    varchar(100) ,
 actual_arrival      varchar(100)
) AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\flights.csv')
--  todo добавить аттрибуты и foreign key
--
-- CREATE TABLE seats (
--  aircraft_code    char(3)  primary key,
--  seat_no          varchar(4)  primary key,
--  fare_conditions  varchar(10),
--  foreign key (aircraft_code) references aircrafts(aircraft_code)
-- )AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\seats.csv');
-- --  todo добавить аттрибуты и foreign key
--
-- CREATE TABLE ticket_flights (
--  ticket_no        char(13)  primary key,
--  flight_id        integer   primary key ,
--  fare_conditions  varchar(10)  ,
--  amount           numeric(10,2),
--   foreign key (ticket_no) references tickets(ticket_no),
--   foreign key (flight_id) references flights(flight_id),
--   foreign key (ticket_no) references boarding_passes(ticket_no),
--   foreign key (flight_id) references boarding_passes(flight_id),
--
-- )AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\ticket_flights.csv');
-- --  todo добавить аттрибуты и foreign key
--
-- CREATE TABLE tickets (
--  ticket_no       char(13) primary key,
--  book_ref        char(6)   ,
--  passenger_id    varchar(20) ,
--  passenger_name  varchar    ,
--  contact_data    varchar,
--    foreign key (book_ref) references bookings(book_ref),
-- )AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\tickets.csv');
-- --  todo добавить аттрибуты и foreign key

