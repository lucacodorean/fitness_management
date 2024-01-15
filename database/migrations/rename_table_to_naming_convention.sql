USE fitness_management_database;

ALTER TABLE schedule RENAME TO trainings;
ALTER TABLE permissions_for_roles RENAME TO permission_role;
ALTER TABLE jurnal_events RENAME TO events;
ALTER TABLE jurnal RENAME TO jurnals;
ALTER TABLE employee RENAME TO employees;