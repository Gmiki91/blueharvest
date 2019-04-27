CREATE TABLE Images (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
image MEDIUMBLOB
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

CREATE TABLE Skills(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
time_to_master TINYINT,
time_to_execute TINYINT,
name varchar(255),
type varchar(255),
description varchar(500)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

CREATE TABLE Characters (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) UNIQUE,
password varchar(255),
enabled int,
role varchar(255),
image_id BIGINT,
food TINYINT,
money MEDIUMINT,
last_visit DATE,
status varchar(255),
FOREIGN KEY (image_id) REFERENCES images(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

CREATE TABLE Skills_learned(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
char_id BIGINT,
skill_id BIGINT,
FOREIGN KEY (char_id) REFERENCES characters(id),
FOREIGN KEY (skill_id) REFERENCES skills(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

INSERT into Skills (time_to_master,time_to_execute, name, type, description) values
(0,-1,"Orrtúrás", "ACTIVE", "Szörnyedbe genetikailag van kódolva a legősibb művészet, az orrtúrás mestersége.");
INSERT into Skills (time_to_master,time_to_execute, name, type, description) values
(10,2,"Vadászat", "ACTIVE", "Szörnyed elmehet élelemre vadászni. Időtartam: 2 óra.");
INSERT into Skills (time_to_master,time_to_execute, name, type, description) values
(3,0,"Írás", "PASSIVE", "Szörnyed képes másoknak üzenetet hagyni.");
INSERT into Skills (time_to_master,time_to_execute, name, type, description) values
(4,0,"Éneklés", "PASSIVE", "Szörnyed hangjával el tudja kápráztatni a körülötte lévőket.");

INSERT INTO `images`(`id`, `image`) VALUES (1,"kép");
INSERT INTO `images`(`id`, `image`) VALUES (2,"kép");
