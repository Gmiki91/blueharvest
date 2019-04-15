CREATE TABLE actions(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
char_id BIGINT,
tatigkeit varchar(255),
end_time DATETIME,
FOREIGN KEY (char_id) REFERENCES characters(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;