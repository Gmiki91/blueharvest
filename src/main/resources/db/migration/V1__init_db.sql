CREATE TABLE Product (
id BIGINT AUTO_INCREMENT,
name VARCHAR(255),
constraint pk_Product PRIMARY KEY (id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;