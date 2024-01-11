/**
 * Grant a permission to a specific role.
 */
 
CREATE PROCEDURE GrantPermissionToRole(
    IN p_role_id INT,
    IN p_permission_id INT
)
BEGIN
    INSERT INTO permissions_for_roles (role_id, permission_id)
    VALUES (p_role_id, p_permission_id);
END
