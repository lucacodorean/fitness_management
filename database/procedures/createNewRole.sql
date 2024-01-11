/**
 * Add a new role to the roles table.
 */
CREATE PROCEDURE AddNewRole(
    IN p_title VARCHAR(20)
)
BEGIN
    INSERT INTO roles (title)
    VALUES (p_title);
END 