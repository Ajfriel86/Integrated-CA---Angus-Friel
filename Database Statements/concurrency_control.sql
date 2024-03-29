-- Set the transaction isolation level to READ COMMITTED.
-- This level guarantees that any data read is committed at the moment it is read.
-- Thus, the transaction can only see data that has been committed before the transaction began,
-- preventing dirty reads. However, it allows for non-repeatable reads and phantom reads.
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- Start a new transaction.
-- This marks the beginning of a transaction block, meant to be treated as a single unit of work.
-- Transactions ensure data integrity and consistency in case of errors or system failures.
START TRANSACTION;

-- Execute a SELECT query within the transaction.
-- This query selects all columns from the 'grades' table where the StudentID equals 123.
-- The READ COMMITTED isolation level affects the visibility of this data, ensuring it's consistent
-- and was committed before the transaction started.
SELECT * FROM grades
WHERE StudentID = 123;

-- Commit the transaction.
-- This command saves all changes made during the transaction to the database.
-- Once committed, the changes are permanent and visible to other transactions,
-- according to their isolation level settings.
COMMIT;
