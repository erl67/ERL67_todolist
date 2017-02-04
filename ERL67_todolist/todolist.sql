USE erl67is1017;

CREATE TABLE IF NOT EXISTS todolist (
    id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL UNIQUE,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
);


ALTER TABLE todolist
ADD UNIQUE (description);

ALTER TABLE `erl67is1017`.`todolist` 
CHANGE COLUMN `timestamp` `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;


INSERT INTO todolist(description, timestamp) VALUES ('eat', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('work', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('study', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('go home', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('sleep', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('clean', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('sleep', CURRENT_TIMESTAMP());
INSERT INTO todolist(description) VALUES ('Shop');


SELECT * FROM erl67is1017.todolist ORDER BY id ASC;

CREATE TABLE `todolist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `description` (`description`)
) ENGINE=MyISAM AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;

