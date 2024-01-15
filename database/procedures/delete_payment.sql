USE fitness_management_database;
DROP PROCEDURE IF EXISTS delete_payment;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS delete_payment(
    IN employee_id INT,
    IN payment_id INT,
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(23, employee_id, ok);

    IF ok THEN
        DELETE FROM payments WHERE id = payment_id;
    END IF;
END //

DELIMITER ;
