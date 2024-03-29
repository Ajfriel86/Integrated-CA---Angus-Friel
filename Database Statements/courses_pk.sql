-- Alter the 'courses' table to add a new constraint
ALTER TABLE courses
ADD CONSTRAINT pk_courses_courseid -- Assigns a name to the constraint for easy reference
PRIMARY KEY (CourseID); -- Designates the 'CourseID' column as the primary key of the table
