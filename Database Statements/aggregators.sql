-- Calculate the average grade for each module
SELECT 
    m.ModuleID,               -- Selects the Module ID from the 'module' table
    m.CourseName,             -- Selects the Course Name from the 'module' table
    AVG(g.GradeValue) AS AverageGrade  -- Calculates the average grade for each module
FROM 
    module m                  -- Specifies the 'module' table as the source of ModuleID and CourseName, aliased as 'm'
JOIN 
    grades g ON m.ModuleID = g.ModuleID  -- Joins the 'grades' table to the 'module' table on ModuleID to correlate modules with their grades
GROUP BY 
    m.ModuleID, m.CourseName; -- Groups the results by Module ID and Course Name to ensure the average is calculated for each module separately
