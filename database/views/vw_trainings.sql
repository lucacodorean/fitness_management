CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `fitness_management_database`.`vw_traings` AS
    SELECT 
        CONCAT(`client`.`firstname`,
                ' ',
                `client`.`lastname`) AS `client`,
        CONCAT(`emp`.`firstname`, ' ', `emp`.`lastname`) AS `trainer`,
        `training`.`time_start` AS `time_start`,
        `training`.`time_end` AS `time_end`,
        `training`.`trainer_id` AS `trainer_id`
    FROM
        ((`fitness_management_database`.`trainings` `training`
        LEFT JOIN `fitness_management_database`.`clients` `client` ON ((`client`.`id` = `training`.`client_id`)))
        LEFT JOIN `fitness_management_database`.`employees` `emp` ON ((`emp`.`id` = `training`.`trainer_id`)))