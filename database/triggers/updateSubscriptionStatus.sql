USE fitness_management_database;

CREATE TRIGGER update_subscription_status
    AFTER INSERT ON payments
    FOR EACH ROW
    UPDATE clients 
		SET next_payment_at = DATE_ADD(next_payment_at, INTERVAL 1 MONTH),
			has_active_sub = true
		WHERE (SELECT client_id FROM payments ORDER BY id DESC LIMIT 1) = clients.id;
