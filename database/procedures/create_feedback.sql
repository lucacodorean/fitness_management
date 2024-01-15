USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_feedback;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_feedback(
    IN client_id INT,
    IN employee_id INT,
    IN rating INT,
    IN description VARCHAR(255)
)
BEGIN
    INSERT INTO feedback (client_id, employee_id, rating, description) 
        VALUES (client_id, employee_id, rating, description);
END //

DELIMITER ;
