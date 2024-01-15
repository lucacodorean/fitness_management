USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_subscription;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_subscription(
    IN price INT,
    IN description VARCHAR(255)
)
BEGIN
    INSERT INTO subscriptions (price,  description) VALUES (price, description);
END //

DELIMITER ;
