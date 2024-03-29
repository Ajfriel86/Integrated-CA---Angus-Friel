-- Calculate the pass rate percentage for each course
SELECT 
    m.CourseName, -- Select the Course Name from the 'module' table
    -- Calculate the pass rate as a percentage
    ROUND((SUM(CASE WHEN g.Status = 'Pass' THEN 1 ELSE 0 END) / COUNT(g.StudentID)) * 100, 2) AS PassRatePercentage
FROM 
    module m -- From the 'module' table, aliased as 'm'
JOIN 
    grades g ON m.ModuleID = g.ModuleID -- Inner join with the 'grades' table on ModuleID to include grades for each module
GROUP BY 
    m.CourseName; -- Group the results by Course Name to calculate the pass rate for each course
