/*
psql -U postgres
CREATE USER administrator WITH PASSWORD 'administrator'
CREATE DATABASE applications_students OWNER administrator;
*/
CREATE TABLE students(
    student_id BIGSERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    average_grade NUMERIC NOT NULL
);

CREATE TABLE applications (
    applicationId BIGSERIAL PRIMARY KEY,
    course TEXT NOT NULL,
    semester TEXT NOT NULL,
    status TEXT NOT NULL,
    student_id INTEGER,

    FOREIGN KEY (student_id) REFERENCES students(student_id));