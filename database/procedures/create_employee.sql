USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_employee;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_employee(
    IN first_name VARCHAR(20), 
    IN last_name VARCHAR(20), 
    IN email_address VARCHAR(25), 
    IN pass VARCHAR(64),
    IN wage INT,
    IN role_id INT,
    IN employee_id INT, 
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(9, employee_id, ok);

    IF ok THEN
        INSERT INTO employees (firstName, lastName, email, pass, wage, role_id) 
            VALUES (first_name, last_name, email_address, pass, wage, role_id);
    END IF;
END //

DELIMITER ;
