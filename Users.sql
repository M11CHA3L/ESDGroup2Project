
CREATE TABLE Users (
  username varchar(20) NOT NULL,
  password varchar(20),
  roleLevel int default 0,
  PRIMARY KEY (username) 
) ;
INSERT INTO "USERS" ("USERNAME", "PASSWORD", "ROLELEVEL") VALUES('admin', '1234', 3);
INSERT INTO "USERS" ("USERNAME", "PASSWORD", "ROLELEVEL") VALUES('driver', '1234', 2);
INSERT INTO "USERS" ("USERNAME", "PASSWORD", "ROLELEVEL") VALUES('customer', '1234', 1);
