USE fitness_management_database;

DELIMITER //

CREATE PROCEDURE IF NOT EXISTS create_client(
    IN first_name VARCHAR(20), 
    IN last_name VARCHAR(20), 
    IN email_address VARCHAR(25), 
    IN employee_id INT, 
    OUT ok BOOLEAN
)
BEGIN
    DECLARE current_permission_id INTEGER;
    DECLARE done BOOLEAN DEFAULT FALSE;

    -- Declare a cursor for the permissions
    DECLARE permissions_granted CURSOR FOR
        SELECT permission_id 
        FROM permissions_for_roles 
        WHERE permissions_for_roles.role_id = (SELECT role_id FROM employee WHERE id = employee_id);

    -- Declare continue handler
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Set the default value for ok
    SET ok = FALSE;

    -- Open the cursor
    OPEN permissions_granted;

    -- Loop through the permissions
    perms: LOOP
        -- Fetch the next permission
        FETCH permissions_granted INTO current_permission_id;

        -- Check if the permission with ID '13' exists
        IF current_permission_id = 13 THEN
            SET ok = TRUE;
            LEAVE perms;
        END IF;
    END LOOP perms;

    -- Close the cursor
    CLOSE permissions_granted;

    -- If ok is true, insert into clients table
    IF ok THEN
        INSERT INTO clients (firstName, lastName, email, subscription_id) VALUES (first_name, last_name, email_address, 4);
    END IF;
END //

DELIMITER ;
