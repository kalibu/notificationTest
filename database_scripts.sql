DROP TABLE IF EXISTS NOTIFICATIONS;
DROP TABLE IF EXISTS CHANNELS;
DROP TABLE IF EXISTS SUBSCRIBED;
DROP TABLE IF EXISTS USERS;

create table USERS(
    user_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone_number VARCHAR(14),
    PRIMARY KEY(user_id)
);

create table SUBSCRIBED (
    subscribed_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    UNIQUE (user_id, category),
    PRIMARY KEY(subscribed_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);

create table CHANNELS (
    channel_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    UNIQUE (user_id, notification_type),
    PRIMARY KEY(channel_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);

create table NOTIFICATIONS (
    notification_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    notification_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(notification_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);