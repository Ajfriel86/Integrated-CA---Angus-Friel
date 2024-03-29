-- This query retrieves information about students, their grades, and corresponding courses.
-- It selects the student ID, student name, course name, and status from the 'students', 'grades', and 'module' tables.
SELECT s.StudentID, s.StudentName, m.CourseName, g.Status
-- Joining the 'students' table with the 'grades' table based on matching StudentID
FROM students s
JOIN grades g ON s.StudentID = g.StudentID
-- Joining the 'grades' table with the 'module' table based on matching ModuleID
JOIN module m ON g.ModuleID = m.ModuleID
-- Ordering the results by student ID and course name
ORDER BY s.StudentID, m.CourseName;
