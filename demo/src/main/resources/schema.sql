CREATE TABLE CURRENCY(
  ID INT NOT NULL AUTO_INCREMENT(100),
  CODE VARCHAR(3) NOT NULL UNIQUE,
  DESCRIPTION VARCHAR(10),
  PRIMARY KEY (ID)
);
