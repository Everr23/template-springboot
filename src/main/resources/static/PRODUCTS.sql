/* CREATE A TABLE */
CREATE TABLE TB_PRODUCTS 
(
  ID NUMBER NOT NULL 
, NAME VARCHAR2(20) NOT NULL 
, PRICE NUMBER 
, STOCK NUMBER NOT NULL 
, DESCRIPTION VARCHAR2(60) 
, CONSTRAINT TB_PRODUCTS_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);


/* CREATE A SEQUENCE FOR ID */
CREATE SEQUENCE PRODUCT_ID_SEQ;


/* INSERT INITIAL DATA */
INSERT INTO TB_PRODUCTS (ID, NAME, PRICE, STOCK, DESCRIPTION)
VALUES (PRODUCT_ID_SEQ.nextval, 'Producto 1', 1000, 25, 'DESCRIPTION P1');
INSERT INTO TB_PRODUCTS (ID, NAME, PRICE, STOCK, DESCRIPTION)
VALUES (PRODUCT_ID_SEQ.nextval, 'Producto 2', 1000, 25, 'DESCRIPTION P2');
INSERT INTO TB_PRODUCTS (ID, NAME, PRICE, STOCK, DESCRIPTION)
VALUES (PRODUCT_ID_SEQ.nextval, 'Producto 3', 1000, 25, 'DESCRIPTION P3');
INSERT INTO TB_PRODUCTS (ID, NAME, PRICE, STOCK, DESCRIPTION)
VALUES (PRODUCT_ID_SEQ.nextval, 'Producto 4', 1000, 25, 'DESCRIPTION P4');
INSERT INTO TB_PRODUCTS (ID, NAME, PRICE, STOCK, DESCRIPTION)
VALUES (PRODUCT_ID_SEQ.nextval, 'Producto 5', 1000, 25, 'DESCRIPTION P5');
   
   