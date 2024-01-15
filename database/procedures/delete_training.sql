USE fitness_management_database;
DROP PROCEDURE IF EXISTS delete_training;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS delete_training(
    IN employee_id INT,
    IN training_id INT
)
BEGIN
    DELETE FROM trainings WHERE id = training_id;
END //

DELIMITER ;
