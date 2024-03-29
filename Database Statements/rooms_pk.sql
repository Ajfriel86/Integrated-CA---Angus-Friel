-- Adding a primary key constraint to the 'rooms' table
ALTER TABLE rooms
-- Naming the constraint as 'pk_rooms_roomid'
ADD CONSTRAINT pk_rooms_roomid
-- Specifying the primary key column as 'RoomID'
PRIMARY KEY (RoomID);
