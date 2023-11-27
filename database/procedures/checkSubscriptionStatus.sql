use fitness_management_database;

DELIMITER //
CREATE PROCEDURE check_subscription_status(IN client_id INT, OUT result BOOLEAN)
BEGIN
	DECLARE subscription_status BOOLEAN;
    DECLARE subscription_time   TIMESTAMP;
    SET @subscription_status  = (SELECT has_active_sub  FROM clients where clients.id = client_id);
    SET @subscription_time    = (SELECT next_payment_at FROM clients where clients.id = client_id);
    
    IF(@subscription_status = 1) THEN 
		IF(TIMESTAMPDIFF(MINUTE, @subscription_time, NOW()) <> 0) THEN SET result = true;
        END IF;
	ELSE SET result = false; 
	END IF;
END // 