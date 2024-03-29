-- This query retrieves information about the status count for each course module.
-- It selects the course name, status, and the count of each status from the 'module' and 'grades' tables.
SELECT m.CourseName, g.Status, COUNT(*) AS StatusCount
-- Joining the 'module' table with the 'grades' table based on matching ModuleID
FROM module m
JOIN grades g ON m.ModuleID = g.ModuleID
-- Grouping the results by course name and status
GROUP BY m.CourseName, g.Status;
