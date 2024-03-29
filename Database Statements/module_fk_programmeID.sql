-- Adding a foreign key constraint to the 'module' table
ALTER TABLE module
-- Naming the constraint as 'fk_module_programmeid'
ADD CONSTRAINT fk_module_programmeid
-- Specifying the foreign key column as 'ProgrammeID' in the 'module' table
FOREIGN KEY (ProgrammeID)
-- Referencing the 'ProgrammeID' column in the 'programmes' table
REFERENCES programmes(ProgrammeID);
