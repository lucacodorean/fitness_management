USE fitness_management_database;
DROP TRIGGER IF EXISTS log_payment_was_made;

DELIMITER //

CREATE TRIGGER log_payment_was_made
  AFTER INSERT ON payments
  FOR EACH ROW
  BEGIN
    INSERT INTO jurnals (client_id, event_id) VALUES (NEW.client_id, 7);
  END //
  
DELIMITER ;