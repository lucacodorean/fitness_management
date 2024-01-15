USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_training;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_training(
    IN client_id INT,
    IN trainer_id INT,
    IN time_start DATETIME,
    IN time_end DATETIME
)
BEGIN
    INSERT INTO trainings (client_id, trainer_id, time_start, time_end) 
        VALUES (client_id, trainer_id, time_start, time_end);
END //

DELIMITER ;
