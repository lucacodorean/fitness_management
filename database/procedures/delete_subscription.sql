USE fitness_management_database;
DROP PROCEDURE IF EXISTS delete_subscription;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS delete_subscription(
    IN employee_id INT,
    IN subscription_id INT,
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(20, employee_id, ok);

    IF ok THEN
        DELETE FROM subscriptions WHERE id = subscription_id;
    END IF;
END //

DELIMITER ;
