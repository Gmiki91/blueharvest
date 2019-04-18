CREATE TABLE actions(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
char_id BIGINT,
skill_id BIGINT,
end_time DATETIME,
FOREIGN KEY (char_id) REFERENCES characters(id),
FOREIGN KEY (skill_id) REFERENCES skills(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;