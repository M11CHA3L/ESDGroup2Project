--setup database with the following details:
--databaseName: alphacab
--username: alphacab
--password: group2

--add/remove DROP Tables if they have already been created
DROP Table JOURNEY;
DROP Table DEMANDS;
DROP Table CUSTOMERS;
DROP Table DRIVERS;
DROP Table USERS;


CREATE TABLE USERS (
  USERNAME VARCHAR(20) NOT NULL,
  PASSWORD VARCHAR(20) NOT NULL,
  USERROLE INT NOT NULL,
  PRIMARY KEY (USERNAME)
);

INSERT INTO USERS (USERNAME, PASSWORD, USERROLE) VALUES
  ('admin', '1234', 3),
  ('driver1', '1234', 2),
  ('driver2', '1234', 2),
  ('driver3', '1234', 2),
  ('customer1', '1234', 1),
  ('customer2', '1234', 1),
  ('customer3', '1234', 1);

---------

CREATE TABLE DRIVERS (
  REGISTRATION VARCHAR(10) NOT NULL,
  NAME VARCHAR(20),
  USERNAME VARCHAR(20) NOT NULL,
  PRIMARY KEY (REGISTRATION),
  FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);

INSERT INTO DRIVERS (REGISTRATION, NAME, USERNAME) VALUES
  ('AK52VZV', 'John Smith', 'driver1'),
  ('BN60WKA', 'Mehmet Aydin', 'driver2'),
  ('R34AKP', 'Mark Johnson', 'driver3');

---------

CREATE TABLE CUSTOMERS (
  ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), -- CUSTOMER_ID
  NAME VARCHAR(20),
  ADDRESS VARCHAR(60),
  USERNAME VARCHAR(20) UNIQUE,
  FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);

INSERT INTO CUSTOMERS (NAME, ADDRESS, USERNAME) VALUES
  ('Eva Smith', '129 Station Rd, London, N3 2AS', 'customer1'),
  ('Rob Manton', '23 Bow Lane, London, N3', 'customer2'),
  ('Bob Currie', '54 Teignmouth Rd, London, NW2', 'customer3'),
  ('Jim Hunter', '765 High Road, London, N12', null),
  ('Phil Johnson', '75 Squires Lane, London, N3', null),
  ('Saim Soyler', '2 Rosemary Ave, London, N3', null),
  ('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', null);

---------

CREATE TABLE DEMANDS (
  ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), -- DEMAND_ID
  NAME VARCHAR(20),
  ADDRESS VARCHAR(60),
  DESTINATION VARCHAR(60),
  DATE date,
  TIME time,
  STATUS VARCHAR(15),
  CUSTOMER_ID INT NOT NULL,
  FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (ID)
);

INSERT INTO DEMANDS (NAME, ADDRESS, DESTINATION, DATE, TIME, STATUS, CUSTOMER_ID) VALUES
  ('Eva Smith', 'Finchley, London', 'King''s Cross Station, London', '2015-10-14', '09:30:00', 'COMPLETE', 1),
  ('Eva Smith', 'Finchley, London', 'Luton Airport, Luton', '2015-10-22', '10:00:00', 'COMPLETE', 1),
  ('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', 'Heathrow Terminal 3, London', '2015-10-14', '12:00:00', 'COMPLETE', 7),
  ('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', '120 Green Lanes, London, N13', '2015-10-15', '06:00:00', 'COMPLETE', 7),
  ('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', '131 Stoke Newington High Road, London, N12', '2015-10-15', '12:00:00', 'COMPLETE', 7);
  
  ---------

CREATE TABLE JOURNEY (
  JID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), -- JOURNEY_ID
  ID INT NOT NULL, -- CUSTOMER_ID
  DESTINATION VARCHAR(60) ,
  DISTANCE INT NOT NULL DEFAULT 1,
  REGISTRATION VARCHAR(10) NOT NULL,
  DATE date NOT NULL,
  TIME time NOT NULL,
  DEMANDS_ID INT NOT NULL,
  FOREIGN KEY (REGISTRATION) REFERENCES DRIVERS (REGISTRATION),
  FOREIGN KEY (ID) REFERENCES CUSTOMERS (ID),
  FOREIGN KEY (DEMANDS_ID) REFERENCES DEMANDS (ID)
);

INSERT INTO JOURNEY (ID, DESTINATION, DISTANCE, REGISTRATION, DATE, TIME, DEMANDS_ID) VALUES
  (1, 'King''s Cross Station, London', 5, 'BN60WKA', '2015-10-14', '09:30:00', 1),
  (7, 'Heathrow Terminal 3, London', 20, 'BN60WKA', '2015-10-14', '12:00:00', 3),
  (7, '120 Green Lanes, London, N13', 7, 'AK52VZV', '2015-10-15', '06:00:00', 4),
  (7, '131 Stoke Newington High Road, London, N12', 8, 'AK52VZV', '2015-10-15', '12:00:00', 5),
  (1, 'Luton Airport, Luton', 30, 'R34AKP', '2015-10-22', '10:00:00', 2);
