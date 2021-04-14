CREATE TABLE aircrafts
(
  aircraft_code CHAR PRIMARY KEY,
  model VARCHAR(60),
  range INT(5)
)
 AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\aircrafts_data.csv');
--  todo добавить foreign key

CREATE TABLE airports
(
  airport_code char(3) primary key,
  airport_name varchar,
  city         varchar,
  timezone     varchar
) AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\airports_data.csv');
--  todo добавить foreign key

CREATE TABLE boarding_passes (
 ticket_no   char(13) primary key,
 flight_id  integer,
 boarding_no integer,
 seat_no     varchar(4)
)AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\boarding_passes.csv');
--  todo добавить foreign key

CREATE TABLE bookings AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\bookings.csv');
--  todo добавить аттрибуты и foreign key

CREATE TABLE flights AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\flights.csv');
--  todo добавить аттрибуты и foreign key

CREATE TABLE seats AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\seats.csv');
--  todo добавить аттрибуты и foreign key

CREATE TABLE ticket_flights AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\ticket_flights.csv');
--  todo добавить аттрибуты и foreign key

CREATE TABLE tickets AS SELECT * FROM CSVREAD('C:\Users\akors\Desktop\Homework\Java2021\src\main\resources\pack\tickets.csv');
--  todo добавить аттрибуты и foreign key

