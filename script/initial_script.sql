CREATE TABLE `user`
(
    `id`         binary(16) NOT NULL,
    `created`    datetime(6) DEFAULT NULL,
    `email`      varchar(255) DEFAULT NULL,
    `is_active`  bit(1) NOT NULL,
    `last_login` datetime(6) DEFAULT NULL,
    `modified`   datetime(6) DEFAULT NULL,
    `name`       varchar(255) DEFAULT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `token`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `phone`
(
    `id`           int NOT NULL,
    `city_code`    varchar(255) DEFAULT NULL,
    `country_code` varchar(255) DEFAULT NULL,
    `number`       varchar(255) DEFAULT NULL,
    `user_id`      binary(16) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `FKn0jatq8w4gvr91f592d2i1h05` (`user_id`),
    CONSTRAINT `FKn0jatq8w4gvr91f592d2i1h05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint DEFAULT NULL
);
