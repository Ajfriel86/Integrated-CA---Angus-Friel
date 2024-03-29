-- Adding a foreign key constraint to the 'courses' table
ALTER TABLE courses
-- Naming the constraint as 'fk_courses_roomid'
ADD CONSTRAINT fk_courses_roomid
-- Specifying the foreign key column as 'RoomID' in the 'courses' table
FOREIGN KEY (RoomID)
-- Referencing the 'RoomID' column in the 'rooms' table
REFERENCES rooms(RoomID);
