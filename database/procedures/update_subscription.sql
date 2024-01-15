USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_subscription;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS update_subscription(
    IN subscription_id INT, 
    IN price INT,
    IN description VARCHAR(255)
)
BEGIN
    UPDATE subscriptions
    SET price = price, description = description
    WHERE id = subscription_id;
END //

DELIMITER ;
