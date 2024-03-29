-- Adding a foreign key constraint to the 'module' table
ALTER TABLE module
-- Naming the constraint as 'fk_module_lecturerid'
ADD CONSTRAINT fk_module_lecturerid
-- Specifying the foreign key column as 'LecturerID' in the 'module' table
FOREIGN KEY (LecturerID)
-- Referencing the 'LecturerID' column in the 'lecturers' table
REFERENCES lecturers(LecturerID);
