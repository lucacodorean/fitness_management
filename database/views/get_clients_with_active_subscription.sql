use fitness_management_database;

CREATE OR REPLACE VIEW GET_CLIENTS_WITH_ACTIVE_SUBSCRIPTION as
	SELECT 
		CONCAT(clients.firstName, ' ', clients.lastName) as "Client's name", 
        clients.email as "Client's contact",
		clients.next_payment_at as "Next billing date",
        subscriptions.description as "Subscription information"
    FROM 	clients JOIN subscriptions 
    WHERE 	clients.subscription_id = subscriptions.id AND 
			clients.has_active_sub = 1;