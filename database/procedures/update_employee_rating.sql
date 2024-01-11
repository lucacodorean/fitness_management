/**
 * Update an employee's rating based on new feedback.
 */

CREATE PROCEDURE UpdateEmployeeRating(
    IN p_employee_id INT,
    IN p_new_rating FLOAT
)
BEGIN
    UPDATE employee
    SET rating = p_new_rating
    WHERE id = p_employee_id;
END 
