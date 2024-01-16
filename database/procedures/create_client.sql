USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_client;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_client(
    IN first_name VARCHAR(20), 
    IN last_name VARCHAR(20), 
    IN email_address VARCHAR(25), 
    IN employee_id INT, 
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(13, employee_id, ok);

    IF ok THEN
        INSERT INTO clients (firstName, lastName, email, subscription_id, next_payment_at) 
            VALUES (first_name, last_name, email_address, 4, CURRENT_TIMESTAMP + INTERVAL 1 MONTH);
    END IF;
END //

DELIMITER ;
