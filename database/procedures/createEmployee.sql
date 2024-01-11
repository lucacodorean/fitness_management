/**
 * Add a new employee to the employee table.
 */
CREATE PROCEDURE AddEmployee(
    IN firstname_param VARCHAR(20),
    IN lastname_param VARCHAR(20),
    IN email_param VARCHAR(25),
    IN pass_param VARCHAR(64),
    IN wage_param INT,
    IN rating_param FLOAT,
    IN employed_at_param TIMESTAMP,
    IN role_id_param INT
)
BEGIN
    INSERT INTO employee (firstname, lastname, email, pass, wage, rating, employed_at, role_id)
    VALUES (firstname_param, lastname_param, email_param, pass_param, wage_param, rating_param, employed_at_param, role_id_param);
END;
