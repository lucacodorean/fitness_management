CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `fitness_management_database`.`VIEW_TRAINER_SCHEDULE` AS
    SELECT 
        (SELECT 
                CONCAT(`fitness_management_database`.`clients`.`firstname`,
                            ' ',
                            `fitness_management_database`.`clients`.`lastname`)
            FROM
                `fitness_management_database`.`clients`
            WHERE
                (`fitness_management_database`.`clients`.`id` = `fitness_management_database`.`schedule`.`client_id`)) AS `Numele clientului`,
        (SELECT 
                CONCAT(`fitness_management_database`.`employee`.`firstname`,
                            ' ',
                            `fitness_management_database`.`employee`.`lastname`)
            FROM
                `fitness_management_database`.`employee`
            WHERE
                (`fitness_management_database`.`employee`.`id` = `fitness_management_database`.`schedule`.`trainer_id`)) AS `Numele antrenorului`,
        `fitness_management_database`.`schedule`.`time_start` AS `time_start`,
        `fitness_management_database`.`schedule`.`time_end` AS `time_end`,
        `fitness_management_database`.`schedule`.`trainer_id` AS `trainer_id`
    FROM
        `fitness_management_database`.`schedule`
    WHERE
        (CAST(`fitness_management_database`.`schedule`.`time_start`
            AS DATE) > (CURDATE() - INTERVAL 1 DAY))
    ORDER BY `fitness_management_database`.`schedule`.`time_start`