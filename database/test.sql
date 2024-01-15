-- VIEWS
SOURCE /var/www/html/database/views/client.sql;

-- TRIGGERS
-- SOURCE /var/www/html/database/triggers/log_client_has_stopped_subscription.sql;
-- SOURCE /var/www/html/database/triggers/log_payment_was_made.sql;
-- SOURCE /var/www/html/database/triggers/update_employee_rating.sql;
-- SOURCE /var/www/html/database/triggers/update_subscription_status.sql;
-- SOURCE /var/www/html/database/procedures/has_employee_permission.sql;

-- PROCEDURES
-- SOURCE /var/www/html/database/procedures/create_client.sql;
-- CALL create_client('John', 'Client', 'john.client3@email.com', 3, @ok);
-- SELECT @ok AS CLIENT_CREATED;

-- SOURCE /var/www/html/database/procedures/create_employee.sql;
-- CALL create_employee('John', 'Casier', 'john.casier3@gmail.com', 'password', 8000, 3, 1, @ok);
-- SELECT @ok AS EMPLOYEE_CREATED;

-- SOURCE /var/www/html/database/procedures/create_feedback.sql;
-- CALL create_feedback(1, 3, 1, 'This is a test feedback');

-- SOURCE /var/www/html/database/procedures/create_event.sql;
-- CALL create_event('This is a test jurnal event');

-- SOURCE /var/www/html/database/procedures/create_jurnal.sql;
-- CALL create_jurnal(1, 1);

-- SOURCE /var/www/html/database/procedures/create_payment.sql;
-- CALL create_payment(1, 1);

-- SOURCE /var/www/html/database/procedures/create_training.sql;
-- CALL create_training(1, 1, '2021-05-05 12:00:00', '2021-05-05 13:00:00');

-- SOURCE /var/www/html/database/procedures/create_subscription.sql;
-- CALL create_subscription(10000, 'This is a test subscription');

-- SOURCE /var/www/html/database/procedures/delete_employee.sql;
-- CALL delete_employee(11, 1, @ok);
-- SELECT @ok AS EMPLOYEE_DELETED;

-- SOURCE /var/www/html/database/procedures/delete_payment.sql;
-- CALL delete_payment(1, 1, @ok);
-- SELECT @ok AS PAYMENT_DELETED;

-- SOURCE /var/www/html/database/procedures/delete_subscription.sql;
-- CALL delete_subscription(1, 10, @ok);
-- SELECT @ok AS SUBSCRIPTION_DELETED;

-- SOURCE /var/www/html/database/procedures/delete_training.sql;
-- CALL delete_training(1, 1);
-- SELECT @ok AS TRAINING_DELETED;

-- SOURCE /var/www/html/database/procedures/fetch_clients.sql;
-- CALL fetch_clients();

-- SOURCE /var/www/html/database/procedures/fetch_employees.sql;
-- CALL fetch_employees();

-- SOURCE /var/www/html/database/procedures/fetch_events.sql;
-- CALL fetch_events();

-- SOURCE /var/www/html/database/procedures/fetch_payments.sql;
-- CALL fetch_payments();

-- SOURCE /var/www/html/database/procedures/update_client.sql;
-- CALL update_client(1, 'New', 'Update', 'client@email.com', 1, @ok);
-- SELECT @ok AS CLIENT_UPDATED;

-- SOURCE /var/www/html/database/procedures/update_employee.sql;
-- CALL update_employee(2, 'New', 'Update', 'employee@email.com', 'password', 13000, 1, 1, @ok);
-- SELECT @ok AS EMPLOYEE_CREATED;

-- SOURCE /var/www/html/database/procedures/update_event.sql;
-- CALL update_event(1, 'Client nou.');

-- SOURCE /var/www/html/database/procedures/update_feedback.sql;
-- CALL update_feedback(12, 1, 1, 1, 'This is an updated feedback');

-- SOURCE /var/www/html/database/procedures/update_payment.sql;
-- CALL update_payment(1, 1, 10000, 'This is an updated payment');

-- SOURCE /var/www/html/database/procedures/update_subscription.sql;
-- CALL update_subscription(4, 0, 'Acesta este un abonament placeholder.');

-- SOURCE /var/www/html/database/procedures/update_training.sql;
-- CALL update_training(2, 1, 1, '2022-05-05 12:00:00', '2022-05-05 13:00:00');