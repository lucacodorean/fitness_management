USE fitness_management_database;
DROP TRIGGER IF EXISTS log_client_has_stopped_subscription;

DELIMITER //

CREATE TRIGGER log_client_has_stopped_subscription
  AFTER UPDATE ON clients
  FOR EACH ROW
  BEGIN
      IF OLD.has_active_sub = true AND NEW.has_active_sub = false THEN
        INSERT INTO jurnals (client_id, event_id) VALUES (NEW.id, 8);
      END IF;

      IF TIMESTAMPDIFF(MONTH, old.next_payment_at, new.next_payment_at) > 0 THEN
        INSERT INTO payments (client_id, subscription_id) VALUES (new.id, new.subscription_id);
      END IF;
  END //

DELIMITER ;
