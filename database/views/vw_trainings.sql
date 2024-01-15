CREATE OR REPLACE VIEW vw_traings AS
SELECT 
    CONCAT(client.firstName, ' ', client.lastName) AS client,
    CONCAT(emp.firstName, ' ', emp.lastName) AS trainer,
    training.time_start,
    training.time_end
FROM trainings training
LEFT JOIN clients AS client ON client.id = training.client_id
LEFT JOIN employees AS emp ON emp.id = training.client_id
