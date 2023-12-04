DELIMITER //
CREATE PROCEDURE get_employee_rating(IN employee_id INT, OUT result FLOAT)
BEGIN
    DECLARE employee_found INT;
    SET @employee_found = (SELECT id FROM employee WHERE employee.id = employee_id);
        
    IF(@employee_found <> 0) THEN
		SET result = (SELECT rating FROM employee WHERE employee.id = employee_id);
    ELSE SET result = 0;
    END IF;
END //