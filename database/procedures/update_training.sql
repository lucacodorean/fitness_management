USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_training;

DELIMITER //

CREATE PROCEDURE update_training(
    IN training_id INT, 
    IN client_id INT,
    IN trainer_id INT,
    IN time_start DATETIME,
    IN time_end DATETIME
)
BEGIN
    UPDATE trainings 
    SET client_id = client_id, trainer_id = trainer_id, time_start = time_start,
        time_end = time_end
    WHERE id = training_id;
END //

DELIMITER ;
