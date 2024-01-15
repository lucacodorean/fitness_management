use fitness_management_database;

CREATE
OR REPLACE VIEW vw_active_clients AS
SELECT
    CONCAT(clients.firstName, ' ', clients.lastName) as "Client's name",
    clients.email as "Client's contact",
    clients.next_payment_at as "Next billing date",
    subscriptions.description as "Subscription information"
FROM
    clients
LEFT JOIN subscriptions ON clients.subscription_id = subscriptions.id
WHERE clients.has_active_sub = 1;