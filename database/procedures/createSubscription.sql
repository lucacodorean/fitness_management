/** 
 * Add a new subscription to the subscriptions table.
 */
CREATE PROCEDURE InsertSubscription(
    IN price_param INT,
    IN description_param VARCHAR(256)
)
BEGIN
    INSERT INTO subscriptions (price, description)
    VALUES (price_param, description_param);
END;
