use fitness_management_database;

DELIMITER //
CREATE PROCEDURE login(IN email_param VARCHAR, IN pass_param VARCHAR, OUT success BOOLEAN)
BEGIN
    DECLARE email_exists BOOLEAN;
	DECLARE password_ 	 VARCHAR(64);
    SET @email_exists  = (SELECT   id FROM employee WHERE employee.email = email_param);

    IF(@email_exists > 0) THEN 
        SET @password_ = (SELECT pass FROM employee WHERE employee.email = email_param);
        IF(STRCMP(@password_, pass_param) = 0) THEN
            SET success = true;
        ELSE SET success = false;
        END IF;
        ELSE SET @password = "NULL";
    END IF;
END //