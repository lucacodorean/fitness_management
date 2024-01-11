/**
 * This view provides a summary of employees and their roles within the fitness
 * management system.
*/

CREATE VIEW Employee_Role_View AS
SELECT
    e.id AS employee_id,
    e.firstname,
    e.lastname,
    e.email,
    r.title AS role_title
FROM
    employee e
INNER JOIN
    roles r ON e.role_id = r.id;
