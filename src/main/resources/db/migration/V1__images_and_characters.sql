CREATE TABLE Images (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
image MEDIUMBLOB
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

CREATE TABLE Characters (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
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