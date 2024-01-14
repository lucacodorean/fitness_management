/**
 * This view provides a log of client activities recorded in the journal, 
 * including client details, event descriptions, and timestamps.
*/

CREATE VIEW Client_Journal_View AS
SELECT
    j.id AS journal_id,
    c.id AS client_id,
    c.firstname,
    c.lastname,
    je.descr AS event_description,
    j.created_at
FROM
    jurnal j
INNER JOIN
    clients c ON j.client_id = c.id
INNER JOIN
    jurnal_events je ON j.event_id = je.id;
