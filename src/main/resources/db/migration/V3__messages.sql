CREATE TABLE Messages(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
from_name VARCHAR(255),
to_name VARCHAR(255),
subject varchar(255),
content varchar(1000),
sending_time TIMESTAMP,
FOREIGN KEY (from_name) REFERENCES characters(name),
FOREIGN KEY (to_name) REFERENCES characters(name)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;