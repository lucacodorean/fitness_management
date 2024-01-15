USE fitness_management_database;

CREATE OR REPLACE VIEW client AS
SELECT
    client.id,
    client.firstname,
    client.lastname,
    client.email,
    latest_jurnal.descr AS last_event,
    subscription.description AS subscription_description,
    client.next_payment_at,
    latest_feedback.description AS last_feedback
FROM
    clients client
LEFT JOIN
    (
        SELECT events.*, max_event.client_id FROM events
        INNER JOIN (
            SELECT event_id, jurnals.client_id FROM jurnals
            INNER JOIN (
                SELECT 
                    client_id,
                    MAX(id) AS max_jurnal_id
                FROM jurnals
                GROUP BY client_id
            ) AS max_jurnal ON jurnals.id = max_jurnal.max_jurnal_id
        ) AS max_event ON events.id = max_event.event_id
    ) AS latest_jurnal ON client.id = latest_jurnal.client_id
LEFT JOIN
    subscriptions subscription ON client.subscription_id = subscription.id
LEFT JOIN
    (
        SELECT 
            feedback.* 
        FROM feedback
        INNER JOIN (
            SELECT 
                client_id,
                MAX(id) AS max_feedback_id
            FROM feedback
            GROUP BY client_id
        ) max_feedback ON feedback.id = max_feedback.max_feedback_id
    ) AS latest_feedback ON client.id = latest_feedback.client_id;
