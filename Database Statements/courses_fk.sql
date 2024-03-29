-- Add a foreign key constraint named 'fk_courses_programmeid' to the 'courses' table.
-- This constraint links the 'ProgrammeID' column in the 'courses' table
-- with the 'ProgrammeID' column in the 'programmes' table.
-- The constraint ensures that every 'ProgrammeID' value in the 'courses' table
-- must exist as a 'ProgrammeID' value in the 'programmes' table, 
-- establishing a referential integrity between the two tables.
ALTER TABLE courses
ADD CONSTRAINT fk_courses_programmeid FOREIGN KEY (ProgrammeID) REFERENCES programmes(ProgrammeID);

-- Add a foreign key constraint named 'fk_courses_lecturerid' to the 'courses' table.
-- This constraint links the 'LecturerID' column in the 'courses' table
-- with the 'LecturerID' column in the 'lecturers' table.
-- It ensures that every 'LecturerID' in the 'courses' table corresponds to
-- an existing 'LecturerID' in the 'lecturers' table, maintaining referential integrity.
ALTER TABLE courses
ADD CONSTRAINT fk_courses_lecturerid FOREIGN KEY (LecturerID) REFERENCES lecturers(LecturerID);

-- Add a foreign key constraint named 'fk_courses_roomid' to the 'courses' table.
-- This constraint associates the 'RoomID' column in the 'courses' table
-- with the 'RoomID' column in the 'rooms' table.
-- It guarantees that every 'RoomID' listed in the 'courses' table matches
-- a 'RoomID' in the 'rooms' table, ensuring referential integrity across these tables.
ALTER TABLE courses
ADD CONSTRAINT fk_courses_roomid FOREIGN KEY (RoomID) REFERENCES rooms(RoomID);
