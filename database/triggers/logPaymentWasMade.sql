--  Log an event whenever a client makes a payment.

DROP TRIGGER IF EXISTS after_insert_payment;

DELIMITER //
CREATE TRIGGER after_insert_payment
AFTER INSERT ON payments
FOR EACH ROW
BEGIN
  INSERT INTO jurnal (client_id, event_id) VALUES (NEW.client_id, 7);
END //
DELIMITER ;