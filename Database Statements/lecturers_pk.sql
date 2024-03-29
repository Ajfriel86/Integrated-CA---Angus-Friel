-- Adding a primary key constraint to the 'lecturers' table
ALTER TABLE lecturers
-- Naming the constraint as 'pk_lecturers_lecturerid'
ADD CONSTRAINT pk_lecturers_lecturerid
-- Specifying the primary key column as 'LecturerID'
PRIMARY KEY (LecturerID);
