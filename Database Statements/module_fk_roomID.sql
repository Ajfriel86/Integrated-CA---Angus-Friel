-- Adding a foreign key constraint to the 'module' table
ALTER TABLE module
-- Naming the constraint as 'fk_module_roomid'
ADD CONSTRAINT fk_module_roomid
-- Specifying the foreign key column as 'RoomID' in the 'module' table
FOREIGN KEY (RoomID)
-- Referencing the 'RoomID' column in the 'rooms' table
REFERENCES rooms(RoomID);
