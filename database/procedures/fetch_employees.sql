USE fitness_management_database;
DROP PROCEDURE IF EXISTS fetch_employees;

DELIMITER //

CREATE PROCEDURE fetch_employees()
BEGIN
    SELECT * FROM employees;
END //

DELIMITER ;
