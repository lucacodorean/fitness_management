USE fitness_management_database;

DROP PROCEDURE IF EXISTS has_employee_permission;

DELIMITER //

CREATE PROCEDURE has_employee_permission(
    IN permission_id INT,
    IN employee_id INT, 
    OUT ok BOOLEAN
)
BEGIN
    DECLARE current_permission_id INTEGER;

    -- Declare a cursor for the permissions
    DECLARE permissions_granted CURSOR FOR
        SELECT permission_id 
        FROM permission_role 
        WHERE permission_role.role_id = (SELECT role_id FROM employees WHERE id = employee_id);

    -- Set the default value for ok
    SET ok = FALSE;

    -- Open the cursor
    OPEN permissions_granted;

    -- Loop through the permissions
    perms: LOOP
        -- Fetch the next permission
        FETCH permissions_granted INTO current_permission_id;

        IF current_permission_id = permission_id THEN
            SET ok = TRUE;
            LEAVE perms;
        END IF;
    END LOOP perms;

    -- Close the cursor
    CLOSE permissions_granted;
END //

DELIMITER ;
