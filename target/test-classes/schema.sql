DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON (
   ID INT NOT NULL AUTO_INCREMENT,
   BALANCE DOUBLE DEFAULT NULL,
   FIRST_NAME varchar(255) DEFAULT NULL,
   LAST_NAME varchar(255) DEFAULT NULL,
   PRIMARY KEY (ID)
);




