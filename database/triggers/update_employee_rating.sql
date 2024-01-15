USE fitness_management_database;
DROP TRIGGER IF EXISTS update_employee_rating;

DELIMITER //

CREATE TRIGGER update_employee_rating
    AFTER INSERT ON feedback
    FOR EACH ROW
    BEGIN
       UPDATE employees
            SET rating = (
                SELECT AVG(rating) FROM feedback WHERE employee_id = NEW.employee_id
            ) 
            WHERE id = NEW.employee_id;
    END //

DELIMITER ;