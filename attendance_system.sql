CREATE DATABASE attendance_system;
USE attendance_system;

CREATE TABLE students (
  students_id INT auto_increment PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100)
);

CREATE TABLE courses(
   course_id INT AUTO_INCREMENT PRIMARY KEY,
   course_name VARCHAR(100)
);

CREATE TABLE attendance(
	id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    date DATE,
    status VARCHAR(10)
);

SELECT * from attendance;
