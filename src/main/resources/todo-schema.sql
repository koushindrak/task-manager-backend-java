CREATE DATABASE IF NOT EXISTS todolist1;
use todolist1;
set foreign_key_checks = 0;
CREATE TABLE IF NOT EXISTS `comments` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `content` varchar(500) NOT NULL,
    `task_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKi7pp0331nbiwd2844kg78kfwb` (`task_id`),
    CONSTRAINT `FKi7pp0331nbiwd2844kg78kfwb` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `group_details` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name` varchar(50) NOT NULL,
    `owner_id` bigint DEFAULT NULL,
    `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `groups_users` (
                                              `user_id` bigint NOT NULL,
                                              `group_id` bigint NOT NULL,
                                              PRIMARY KEY (`user_id`,`group_id`),
    KEY `FKc1v4br5498d1s8gvcelan45qe` (`group_id`),
    CONSTRAINT `FK3smvbqqwrv7ihunxxb7m899ao` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKc1v4br5498d1s8gvcelan45qe` FOREIGN KEY (`group_id`) REFERENCES `group_details` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `labels` (
                                        `id` bigint NOT NULL AUTO_INCREMENT,
                                        `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    `name` varchar(20) NOT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKjwe9grxkwp2ojnomdy3y1sq02` (`user_id`),
    CONSTRAINT `FKjwe9grxkwp2ojnomdy3y1sq02` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `labels_tasks` (
                                              `labels_id` bigint NOT NULL,
                                              `tasks_id` bigint NOT NULL,
                                              PRIMARY KEY (`labels_id`,`tasks_id`),
    KEY `FKlx3jtdttowhjki86uq93xxxoq` (`tasks_id`),
    CONSTRAINT `FKlx3jtdttowhjki86uq93xxxoq` FOREIGN KEY (`tasks_id`) REFERENCES `tasks` (`id`),
    CONSTRAINT `FKqmv63hvju1l3u6a44tu2m0yop` FOREIGN KEY (`labels_id`) REFERENCES `labels` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `login_details` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `is_expired` bit(1) DEFAULT NULL,
    `logged_in_time` datetime(6) NOT NULL,
    `logged_out_time` datetime(6) DEFAULT NULL,
    `is_revoked` bit(1) DEFAULT NULL,
    `token` varchar(255) NOT NULL,
    `token_type` enum('BEARER') NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ku4vjd6iq1qdm5qgvfbv79uf5` (`token`),
    KEY `FKs6vh3avegq668p7dwolgtn9wr` (`user_id`),
    CONSTRAINT `FKs6vh3avegq668p7dwolgtn9wr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `notifications` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `createdAt` datetime(6) NOT NULL,
    `description` varchar(255) NOT NULL,
    `message` text NOT NULL,
    `task_id` bigint DEFAULT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK2ktjq1slw0ldkuy5rx8fbte2p` (`task_id`),
    CONSTRAINT `FK2ktjq1slw0ldkuy5rx8fbte2p` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `projects` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `end_date` datetime(6) DEFAULT NULL,
    `name` varchar(100) DEFAULT NULL,
    `start_date` datetime(6) DEFAULT NULL,
    `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKhswfwa3ga88vxv1pmboss6jhm` (`user_id`),
    CONSTRAINT `FKhswfwa3ga88vxv1pmboss6jhm` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `roles` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tasks` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `due_date` datetime(6) DEFAULT NULL,
    `name` varchar(50) NOT NULL,
    `priority` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
    `status` enum('TODO','INPROGRESS','DONE') DEFAULT NULL,
    `group_id` bigint DEFAULT NULL,
    `project_id` bigint DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKt4iocteeoc9ygnv6ky6758rwg` (`group_id`),
    KEY `FKsfhn82y57i3k9uxww1s007acc` (`project_id`),
    KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`),
    CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKsfhn82y57i3k9uxww1s007acc` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
    CONSTRAINT `FKt4iocteeoc9ygnv6ky6758rwg` FOREIGN KEY (`group_id`) REFERENCES `group_details` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `users` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `createdAt` datetime(6) NOT NULL,
    `createdBy` varchar(255) NOT NULL,
    `updatedAt` datetime(6) DEFAULT NULL,
    `updatedBy` varchar(255) DEFAULT NULL,
    `email` varchar(100) NOT NULL,
    `enabled` bit(1) NOT NULL,
    `first_name` varchar(50) DEFAULT NULL,
    `last_name` varchar(50) DEFAULT NULL,
    `password` varchar(255) NOT NULL,
    `phone_number` varchar(12) DEFAULT NULL,
    `role` enum('ADMIN','USER') DEFAULT NULL,
    `username` varchar(20) DEFAULT NULL,
    `verification_code` varchar(64) DEFAULT NULL,
    `code_expired` bit(1) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
    UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `users_roles` (
                                             `user_id` bigint NOT NULL,
                                             `role_id` int NOT NULL,
                                             PRIMARY KEY (`user_id`,`role_id`),
    KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
    CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO roles(id,name) VALUES(1,'ROLE_USER'), (2,'ROLE_MODERATOR'), (3,'ROLE_ADMIN');
set foreign_key_checks = 0;

