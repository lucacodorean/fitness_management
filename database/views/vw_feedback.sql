CREATE OR REPLACE VIEW vw_feedback AS
SELECT 
    CONCAT(client.firstName, ' ', client.lastName) AS client_name,
    CONCAT(emp.firstName, ' ', emp.lastName) AS employee_name,
    client.avg_rating AS average_given_rating,
    feedback.rating,
    feedback.description
FROM feedback
LEFT JOIN employees AS emp ON feedback.employee_id = emp.id
LEFT JOIN (
    SELECT 
        clients.*,
        AVG(feedback.rating) AS avg_rating
    FROM clients
    LEFT JOIN feedback ON clients.id = feedback.client_id
    GROUP BY clients.id, clients.firstName, clients.lastName
) AS client ON feedback.client_id = client.id;
