USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_client;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS update_client(
    IN client_id INT,
    IN first_name VARCHAR(20), 
    IN last_name VARCHAR(20), 
    IN email_address VARCHAR(25), 
    IN employee_id INT, 
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(15, employee_id, ok);

    IF ok THEN
        UPDATE clients 
        SET firstName = first_name, lastName = last_name, email = email_address
        WHERE id = client_id;
    END IF;
END //

DELIMITER ;
