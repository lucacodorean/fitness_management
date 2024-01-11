/**
 * This view displays a log of all payments made by clients, including client 
 * details, subscription information, and payment timestamp.
*/
CREATE VIEW Payment_Log_View AS
SELECT
    p.id AS payment_id,
    c.id AS client_id,
    c.firstname,
    c.lastname,
    s.description AS subscription_description,
    p.payed_at
FROM
    payments p
INNER JOIN
    clients c ON p.client_id = c.id
INNER JOIN
    subscriptions s ON p.subscription_id = s.id;