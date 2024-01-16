DELIMITER //
DROP TRIGGER IF EXISTS log_payment;

CREATE TRIGGER log_payment
  AFTER UPDATE ON clients
  FOR EACH ROW
  BEGIN
	IF TIMESTAMPDIFF(MONTH, old.next_payment_at, new.next_payment_at) > 0 THEN
		INSERT INTO payments (client_id, subscription_id) VALUES (new.id, new.subscription_id);
	END IF;
  END //
  
DELIMITER ;