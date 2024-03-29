-- Adding a primary key constraint to the 'module' table
ALTER TABLE module
-- Naming the constraint as 'pk_module_moduleid'
ADD CONSTRAINT pk_module_moduleid
-- Specifying the primary key column as 'ModuleID'
PRIMARY KEY (ModuleID);
