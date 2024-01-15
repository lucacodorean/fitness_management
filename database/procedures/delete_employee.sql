USE fitness_management_database;
DROP PROCEDURE IF EXISTS delete_employee;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS delete_employee(
    IN admin_id INT,
    IN employee_id INT,
    OUT ok BOOLEAN
)
BEGIN
    CALL has_employee_permission(12, admin_id, ok);

    IF ok THEN
        DELETE FROM employees WHERE id = employee_id;
    END IF;
END //

DELIMITER ;
