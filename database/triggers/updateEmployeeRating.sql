USE fitness_management_database;


CREATE TRIGGER update_employee_rating
    AFTER INSERT ON feedback
    FOR EACH ROW
       UPDATE employee 
            SET rating = (SELECT AVG(rating) 
						  FROM feedback 
						  WHERE employee_id = (SELECT employee_id FROM feedback ORDER BY id DESC LIMIT 1)) 
            WHERE id = (SELECT employee_id FROM feedback ORDER BY id DESC LIMIT 1);