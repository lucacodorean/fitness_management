USE fitness_management_database;
DROP PROCEDURE IF EXISTS update_event;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS update_event(
    IN event_id INT, 
    IN description VARCHAR(255)
)
BEGIN
    UPDATE events 
    SET descr = description
    WHERE id = event_id;
END //

DELIMITER ;
