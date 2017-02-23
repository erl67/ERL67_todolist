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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

ALTER TABLE todolist ENGINE = InnoDB; 
ALTER TABLE todolist DROP INDEX description_2;

CREATE TABLE IF NOT EXISTS `user` (
    `user_id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `fname` VARCHAR(50) NOT NULL,
    `lname` VARCHAR(50) NOT NULL
) ENGINE=InnoDB;


-- Randomly generated names
INSERT INTO user (fname, lname) VALUES ('Zhannochka', 'Yevdokiya');
INSERT INTO user (fname, lname) VALUES ('Bruce', 'Wayne');
INSERT INTO user (fname, lname) VALUES ('Heleen', 'Temitope');
INSERT INTO user (fname, lname) VALUES ('Eydís', 'Ashok');
INSERT INTO user (fname, lname) VALUES ('Jeronim', 'Kostyantyn');
INSERT INTO user (fname, lname) VALUES ('Gorden', 'Piripi');
INSERT INTO user (fname, lname) VALUES ('Susanna', 'Moyer');
INSERT INTO user (fname, lname) VALUES ('Bogdana', 'Zhanna');
INSERT INTO user (fname, lname) VALUES ('Eric', 'RL67');
INSERT INTO user (fname, lname) VALUES ('User', 'Ten');

CREATE TABLE IF NOT EXISTS `user_todo` (
    `fk_todo_id` INT UNIQUE NOT NULL,
    `fk_user_id` INT NOT NULL,
	PRIMARY KEY (fk_todo_id, fk_user_id)
) ENGINE=InnoDB ;
ALTER TABLE user_todo
ADD FOREIGN KEY (fk_todo_id) REFERENCES todolist(id),
ADD FOREIGN KEY (fk_user_id) REFERENCES user(user_id)
ON DELETE CASCADE	
ON UPDATE CASCADE;

INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (1, 9);
INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (5, 8);
INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (6, 7);
INSERT INTO todolist(description, timestamp) VALUES ('T1', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('T2', CURRENT_TIMESTAMP());
INSERT INTO todolist(description, timestamp) VALUES ('T3', CURRENT_TIMESTAMP());

DELETE FROM user_todo WHERE fk_todo_id > 68;
DELETE FROM user WHERE user_id > 10;
DELETE FROM todolist WHERE id > 68;

SELECT max(id) FROM erl67is1017.todolist;



# Changes for trees

alter table todolist ADD (fk_id int);
ALTER table todolist ADD FOREIGN KEY (fk_id) REFERENCES todolist(id);

INSERT INTO todolist(description, timestamp, fk_id) VALUES ('TreeTest1', CURRENT_TIMESTAMP(), 211);
INSERT INTO todolist(description, timestamp, fk_id) VALUES ('Drink', CURRENT_TIMESTAMP(), 1);
INSERT INTO todolist(description, timestamp, fk_id) VALUES ('Wake up', CURRENT_TIMESTAMP(), 5);

INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (212, 4);
INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (213, 9);
INSERT INTO user_todo (fk_todo_id, fk_user_id) VALUES (214, 8);