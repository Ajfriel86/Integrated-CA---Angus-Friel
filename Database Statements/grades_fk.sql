-- Alter the 'grades' table to add a foreign key constraint related to the 'students' table
ALTER TABLE grades
ADD CONSTRAINT fk_grades_studentid -- Names the constraint for easier reference and management
FOREIGN KEY (StudentID) -- Specifies the column in the 'grades' table that will act as the foreign key
REFERENCES students(StudentID); -- Indicates the primary key column of the 'students' table that the foreign key column references

-- Alter the 'grades' table to add a foreign key constraint related to the 'programmes' table
ALTER TABLE grades
ADD CONSTRAINT fk_grades_programmeid -- Names the constraint for easier reference and management
FOREIGN KEY (ProgrammeID) -- Specifies the column in the 'grades' table that will act as the foreign key
REFERENCES programmes(ProgrammeID); -- Indicates the primary key column of the 'programmes' table that the foreign key column references

-- Alter the 'grades' table to add a foreign key constraint related to the 'module' table
ALTER TABLE grades
ADD CONSTRAINT fk_grades_moduleid -- Names the constraint for easier reference and management
FOREIGN KEY (ModuleID) -- Specifies the column in the 'grades' table that will act as the foreign key
REFERENCES module(ModuleID); -- Indicates the primary key column of the 'module' table that the foreign key column references
