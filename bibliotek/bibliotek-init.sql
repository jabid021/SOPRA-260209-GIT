CREATE DATABASE bibiotek;

USE bibiotek;


CREATE TABLE `utilisateur` (
  `id` varchar(255) NOT NULL,
  `password` varchar(150) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO utilisateur (id, username, password) VALUES ('jeremy', 'jeremy', '$2a$10$zFeTn0rQKrsMXIT2I2NAl.70YWXs05/XyJsnSsznDjB4C.T0yv8hC');
