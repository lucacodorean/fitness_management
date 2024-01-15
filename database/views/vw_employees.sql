CREATE OR REPLACE VIEW vw_employee AS
SELECT 
    emp.firstname,
    emp.lastname,
    emp.email,
    emp.wage,
    emp.rating,
    latest_feedback.description AS lastest_feedback,
    rol.title AS role,
    next_training.time_start AS next_training_at,
    CONCAT(client.firstName, ' ', client.lastName) AS client,
    emp.employed_at
FROM employees emp
LEFT JOIN
    (
        SELECT time_start, client_id, correct_training.trainer_id FROM trainings
        INNER JOIN (
            SELECT 
                trainer_id,
                MIN(time_start) AS next_training_at
            FROM trainings
            WHERE time_start > NOW()
            GROUP BY trainer_id
        ) correct_training ON trainings.time_start = correct_training.next_training_at
    ) AS next_training ON emp.id = next_training.trainer_id
LEFT JOIN clients AS client ON client.id = next_training.client_id
LEFT JOIN
    (
        SELECT 
            feedback.* 
        FROM feedback
        INNER JOIN (
            SELECT 
                employee_id,
                MAX(id) AS max_feedback_id
            FROM feedback
            GROUP BY employee_id
        ) max_feedback ON feedback.id = max_feedback.max_feedback_id
    ) AS latest_feedback ON emp.id = latest_feedback.employee_id
LEFT JOIN roles AS rol ON emp.role_id = rol.id;