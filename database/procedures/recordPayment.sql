DELIMITER //
CREATE PROCEDURE MakePayment(
    IN p_client_id INT,
    IN p_subscription_id INT
)
BEGIN
    INSERT INTO payments (client_id, subscription_id)
    VALUES (p_client_id, p_subscription_id);
END  //
DELIMITER ;