USE fitness_management_database;
DROP PROCEDURE IF EXISTS fetch_events;

DELIMITER //

CREATE PROCEDURE fetch_events()
BEGIN
    SELECT * FROM events;
END //

DELIMITER ;
