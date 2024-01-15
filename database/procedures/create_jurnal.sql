USE fitness_management_database;
DROP PROCEDURE IF EXISTS create_jurnal;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_jurnal(
    IN client_id INT,
    IN event_id INT
)
BEGIN
    INSERT INTO jurnals (client_id, event_id) VALUES (client_id, event_id);
END //

DELIMITER ;
