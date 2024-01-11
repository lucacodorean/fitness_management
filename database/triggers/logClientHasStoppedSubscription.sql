/** 
 * Log an event when a client's subscription is deactivated.
 */

CREATE TRIGGER after_update_client_subscription
AFTER UPDATE ON clients
FOR EACH ROW
BEGIN
    IF OLD.has_active_sub = true AND NEW.has_active_sub = false THEN
      INSERT INTO jurnal (client_id, event_id) VALUES (NEW.id, 8);
    END IF;
END;
