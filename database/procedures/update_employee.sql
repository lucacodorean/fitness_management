USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_employee;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS update_employee(
    IN target_id INT,
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
    CALL has_employee_permission(15, employee_id, ok);

    IF ok THEN
        UPDATE employees 
        SET firstName = first_name, lastName = last_name, email = email_address,
            pass = pass, wage = wage, role_id = role_id
        WHERE id = target_id;
    END IF;
END //

DELIMITER ;
