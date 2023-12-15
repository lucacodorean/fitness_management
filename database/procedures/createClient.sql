use fitness_management_database;

CREATE PROCEDURE IF NOT EXISTS create_client(
    IN first_name VARCHAR(20),    IN last_name VARCHAR(20), 
    IN email_address VARCHAR(25), IN employee_id INT, OUT ok BOOLEAN)
BEGIN
	DECLARE current_permission_id INTEGER DEFAULT 1;
    
    DECLARE permissions_granted CURSOR FOR
		SELECT permission_id 
		FROM   permissions_for_roles 
		WHERE  permissions_for_roles.role_id = (SELECT role_id from employee where id = employee_id);
    
	OPEN permissions_granted;

	perms: LOOP
		FETCH NEXT FROM permissions_granted INTO current_permission_id;
		IF(current_permission_id = '13') THEN
			SET ok = 1;
            LEAVE perms;
		END IF;
	END LOOP perms;
    
	IF(ok = 1) THEN 
		INSERT INTO clients(firstName, lastName, email, subscription_id) VALUES (first_name, last_name, email_address, 4);
	END IF;

    CLOSE permissions_granted;
END
