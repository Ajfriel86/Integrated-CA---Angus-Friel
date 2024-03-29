-- Modifying the 'StaffUserID' column in the 'staff' table
ALTER TABLE staff
-- Modifying the data type to INT, enabling AUTO_INCREMENT, and setting it as the primary key
MODIFY COLUMN StaffUserID INT AUTO_INCREMENT PRIMARY KEY;
