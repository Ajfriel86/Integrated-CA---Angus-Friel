-- Adding a foreign key constraint to the 'grades' table
ALTER TABLE grades
-- Naming the constraint as 'fk_grades_moduleid'
ADD CONSTRAINT fk_grades_moduleid
-- Specifying the foreign key column as 'ModuleID' in the 'grades' table
FOREIGN KEY (ModuleID)
-- Referencing the 'ModuleID' column in the 'module' table
REFERENCES module(ModuleID);
