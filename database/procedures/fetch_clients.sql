USE fitness_management_database;
DROP PROCEDURE IF EXISTS fetch_clients;

DELIMITER //

CREATE PROCEDURE fetch_clients()
BEGIN
    SELECT * FROM clients;
END //

DELIMITER ;
