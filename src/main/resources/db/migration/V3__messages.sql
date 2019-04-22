CREATE TABLE Messages(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
from_id BIGINT,
to_id BIGINT,
subject varchar(255),
content varchar(1000),
FOREIGN KEY (from_id) REFERENCES characters(id),
FOREIGN KEY (to_id) REFERENCES characters(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;