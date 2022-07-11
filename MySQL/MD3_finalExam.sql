CREATE DATABASE `MD3_FinalExam`;
USE MD3_FinalExam;

CREATE TABLE Deparment (
Iddb int primary key auto_increment,
 NameDp varchar(50) );

CREATE TABLE Staff (
Id int primary key auto_increment,
Name varchar(50) not null,
DateOfBirth Date not null,
Address varchar(100) not null,
PhoneNumber varchar(10) not null,
Email varchar(50) not null,
Iddb int,
foreign key(Iddb) references Deparment(Iddb) );

INSERT INTO Deparment values
(1, "Hành chính"), (2, "Kế toán"),  (3, "Kĩ thuật");

INSERT INTO Staff values 
(1,"Trần Minh Trang", "2000-04-13", "Hà Nội", "0912113113", "trang@gmail.com", 1),
(2, "Trần Thu Hà", "2000-03-11", "Hà Nội", "0912112112", "ha@gmail.com", 2),
(3, "Nguyễn Minh Châu", "2000-09-11", "Hà Nội", "0968132112", "chau@gmail.com", 2),
(4, "Nguyễn Lê Minh", "2000-08-01", "Hà Nội", "0968132342", "minh@gmail.com", 2),
(5, "Nguyễn Hải Dương", "2000-08-01", "Hà Nội", "0911132345", "duong@gmail.com", 3),
(6, "Nguyễn Lan Phương", "2000-06-05", "Hà Nội", "0971112345", "phuong@gmail.com", 3);