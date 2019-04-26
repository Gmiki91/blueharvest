CREATE TABLE items(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
description VARCHAR(500),
price MEDIUMINT,
quantity SMALLINT,
type VARCHAR(100),
image_id BIGINT,
FOREIGN KEY (image_id) REFERENCES images(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

CREATE TABLE items_owned(
char_id BIGINT,
item_id BIGINT,
quantity SMALLINT,
FOREIGN KEY (char_id) REFERENCES characters(id),
FOREIGN KEY (item_id) REFERENCES items(id)
)
engine=InnoDb character set = UTF8 collate = utf8_hungarian_ci;

INSERT INTO `images`(`id`, `image`) VALUES (41,"kép");
INSERT INTO `images`(`id`, `image`) VALUES (42,"kép");
INSERT INTO `images`(`id`, `image`) VALUES (43,"kép");
INSERT INTO `images`(`id`, `image`) VALUES (44,"kép");

INSERT INTO items (id,name,description,price,quantity,type,image_id)
VALUES(1,"Sisak","Megvédi a kobakot",150,4,"WEARABLE",41);
INSERT INTO items (id,name,description,price,quantity,type,image_id)
VALUES(2,"Mellény","Előkelő alkalmakra",90,3,"WEARABLE",42);
INSERT INTO items (id,name,description,price,quantity,type,image_id)
VALUES(3,"Kék ital","Erősíti a jellemet",25,10,"CONSUMABLE",43);
INSERT INTO items (id,name,description,price,quantity,type,image_id)
VALUES(4,"Piros ital","Laktat",20,12,"CONSUMABLE",44);



