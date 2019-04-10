CREATE TABLE Characters (
id BIGINT AUTO_INCREMENT,
name VARCHAR(255),
constraint pk_Characters PRIMARY KEY (id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;