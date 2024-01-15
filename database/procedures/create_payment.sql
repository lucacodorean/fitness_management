USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_payment;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_payment(
    IN client_id INT,
    IN subscription_id INT
)
BEGIN
    INSERT INTO payments (client_id, subscription_id) 
        VALUES (client_id, subscription_id);
END //

DELIMITER ;
