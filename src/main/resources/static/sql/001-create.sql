# ROLE
CREATE TABLE `role`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO role (id, name)
VALUES (1, 'USER');
INSERT INTO role (id, name)
VALUES (2, 'ADMIN');

# USER
CREATE TABLE `user`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `active`        bit(1)                                  DEFAULT NULL,
    `birth_date`    datetime(6)                             DEFAULT NULL,
    `creation_date` date                                    DEFAULT NULL,
    `email`         varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `image`         varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `last_login`    datetime(6)                             DEFAULT NULL,
    `name`          varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `password`      varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `surname`       varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `username`      varchar(255) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
    `role_id`       bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
    CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

INSERT INTO user (active, birth_date, creation_date, email, name, password, surname, username, role_id)
VALUES (1, '2023-08-10 00:00:00.000000', '2023-08-09', 'test@gmail.com', 'Jose', 'test', 'Perez Garcia,', 'jose95', 1);