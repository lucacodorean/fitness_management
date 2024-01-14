use fitness_management_database;

DROP PROCEDURE IF EXISTS check_subscription_status;

DELIMITER //
CREATE PROCEDURE check_subscription_status(IN client_id INT, OUT result BOOLEAN)
BEGIN
	  DECLARE subscription_status BOOLEAN;
    SET @subscription_status  = (SELECT has_active_sub  FROM clients where id = client_id);
    
    IF(@subscription_status = 1) THEN SET result = true;
	  ELSE SET result = false; 
	END IF;
END // 
DELIMITER ;