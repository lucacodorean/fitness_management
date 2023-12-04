use fitness_management_database;

CREATE TABLE IF NOT EXISTS schedule (
    id              INT        NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    client_id       INT        NOT NULL,
    trainer_id      INT        NOT NULL,
    time_start      TIMESTAMP  NOT NULL,
    time_end        TIMESTAMP  NOT NULL,

    CONSTRAINT client_schedule  FOREIGN KEY(client_id)  REFERENCES clients(id),
    CONSTRAINT trainer_schedule FOREIGN KEY(trainer_id) REFERENCES employee(id)
);