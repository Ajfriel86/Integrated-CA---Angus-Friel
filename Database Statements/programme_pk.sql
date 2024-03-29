-- Adding a primary key constraint to the 'programmes' table
ALTER TABLE programmes
-- Naming the constraint as 'pk_programmes_programmeid'
ADD CONSTRAINT pk_programmes_programmeid
-- Specifying the primary key column as 'ProgrammeID'
PRIMARY KEY (ProgrammeID);
