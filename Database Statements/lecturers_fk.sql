-- Adding a foreign key constraint to the 'lecturers' table
ALTER TABLE lecturers
-- Naming the constraint as 'fk_lecturers_programmeid'
ADD CONSTRAINT fk_lecturers_programmeid
-- Specifying the foreign key column as 'ProgrammeID' in the 'lecturers' table
FOREIGN KEY (ProgrammeID)
-- Referencing the 'ProgrammeID' column in the 'programmes' table
REFERENCES programmes(ProgrammeID);
