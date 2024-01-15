USE fitness_management_database;
DROP PROCEDURE IF EXISTS fetch_payments;

DELIMITER //

CREATE PROCEDURE fetch_payments()
BEGIN
    SELECT * FROM payments;
END //

DELIMITER ;
