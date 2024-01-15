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
  END //

DELIMITER ;
