CREATE OR REPLACE VIEW vw_subscriptions AS
SELECT 
    COALESCE(client.activeCount, 0) AS active_count,
    COALESCE(client.totalCount, 0) AS total_count,
    sub.price,
    sub.description
FROM subscriptions sub
LEFT JOIN
    (
        SELECT
            COUNT(CASE WHEN client.has_active_sub = 1 THEN 1 END) AS activeCount,
            COUNT(client.id) AS totalCount,
            client.subscription_id
        FROM clients client
        GROUP BY client.subscription_id
    ) AS client ON sub.id = client.subscription_id
ORDER BY COALESCE(client.activeCount, 0) DESC;