USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_feedback;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS update_feedback(
    IN feedback_id INT, 
    IN client_id INT,
    IN employee_id INT,
    IN rating INT,
    IN description VARCHAR(255)
)
BEGIN
    UPDATE feedback 
    SET client_id = client_id, employee_id = employee_id, rating = rating,
        description = description
    WHERE id = feedback_id;
END //

DELIMITER ;
