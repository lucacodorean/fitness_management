USE fitness_management_database;
DROP TRIGGER IF EXISTS update_subscription_status;

DELIMITER //
CREATE TRIGGER update_subscription_status
    AFTER INSERT ON payments
    FOR EACH ROW
    BEGIN
    UPDATE clients 
		SET next_payment_at = DATE_ADD(NOW(), INTERVAL 1 MONTH),
			  has_active_sub = true
		WHERE id = NEW.client_id;
    END //
DELIMITER ;