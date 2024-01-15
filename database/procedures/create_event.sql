USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_event;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_event(
    IN description VARCHAR(255)
)
BEGIN
    INSERT INTO events (descr) VALUES (description);
END //

DELIMITER ;
