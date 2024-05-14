DROP TABLE IF EXISTS NOTIFICATIONS;
DROP TABLE IF EXISTS CHANNELS;
DROP TABLE IF EXISTS SUBSCRIBED;
DROP TABLE IF EXISTS NOTIFICATION_TYPES;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS USERS;

create table USERS(
    user_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone_number VARCHAR(14),
    PRIMARY KEY(user_id)
);

create table CATEGORIES(
    category_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(category_id)
);

create table NOTIFICATION_TYPES(
    notification_type_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(notification_type_id)
);

create table SUBSCRIBED (
    subscribed_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    UNIQUE (user_id, category_id),
    PRIMARY KEY(subscribed_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id),
    CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES CATEGORIES(category_id)
);

create table CHANNELS (
    channel_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    notification_type_id INT NOT NULL,
    UNIQUE (user_id, notification_type_id),
    PRIMARY KEY(channel_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id),
    CONSTRAINT fk_notification_type FOREIGN KEY(notification_type_id) REFERENCES NOTIFICATION_TYPES(notification_type_id)
);

create table NOTIFICATIONS (
    notification_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    notification_type_id INT NOT NULL,
    notification_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(notification_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id),
    CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES CATEGORIES(category_id),
    CONSTRAINT fk_notification_type FOREIGN KEY(notification_type_id) REFERENCES NOTIFICATION_TYPES(notification_type_id)
);