CREATE TABLE Skills(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
time_to_master TINYINT,
name varchar(255)
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