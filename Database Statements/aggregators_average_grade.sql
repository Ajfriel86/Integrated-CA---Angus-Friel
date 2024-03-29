-- Select data from the 'module' and 'grades' tables
SELECT 
    m.ModuleID,               -- Select the Module ID from the 'module' table
    m.CourseName,             -- Select the Course Name from the 'module' table
    AVG(g.GradeValue) AS AverageGrade  -- Calculate the average grade value from the 'grades' table for each module
FROM 
    module m                  -- From the 'module' table, aliased as 'm'
JOIN 
    grades g ON m.ModuleID = g.ModuleID  -- Inner join with the 'grades' table on ModuleID to include only modules with grades
GROUP BY 
    m.ModuleID, m.CourseName; -- Group the results by Module ID and Course Name to calculate average grades per module

