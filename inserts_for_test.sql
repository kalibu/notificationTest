--USERS
INSERT INTO USERS (name, email, phone_number)
VALUES
('David', 'david@test.com', '+5511999999999');

INSERT INTO USERS (name, email, phone_number)
VALUES
('John', 'john@test.com', '+5511888888888');

INSERT INTO USERS (name, email, phone_number)
VALUES
('Peter', 'peter@test.com', '+5511777777777');

INSERT INTO USERS (name, email, phone_number)
VALUES
('Ana', 'ana@test.com', '+5511666666666');

--SUBSCRIBED
INSERT INTO SUBSCRIBED (user_id, category)
VALUES
(1, 'SPORTS');

INSERT INTO SUBSCRIBED (user_id, category)
VALUES
(2, 'FINANCE');

INSERT INTO SUBSCRIBED (user_id, category)
VALUES
(3, 'MOVIES');

INSERT INTO SUBSCRIBED (user_id, category)
VALUES
(4, 'SPORTS');

--CHANNELS
INSERT INTO CHANNELS (user_id, notification_type)
VALUES
(1, 'SMS');

INSERT INTO CHANNELS (user_id, notification_type)
VALUES
(2, 'E_MAIL');

INSERT INTO CHANNELS (user_id, notification_type)
VALUES
(3, 'PUSH_NOTIFICATION');

INSERT INTO CHANNELS (user_id, notification_type)
VALUES
(4, 'PUSH_NOTIFICATION');