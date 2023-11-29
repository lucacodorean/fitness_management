use fitness_management_database;

CREATE TABLE IF NOT EXISTS feedback(
    id          INT             NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    client_id   INT             NOT NULL,
    employee_id INT             NOT NULL,
    rating      FLOAT           NOT NULL,
    description varchar(256),

    CONSTRAINT fk_client_id_feedback   FOREIGN KEY(client_id)   REFERENCES clients(id),
    CONSTRAINT fk_employee_id_feedback FOREIGN KEY(employee_id) REFERENCES employee(id)
);

