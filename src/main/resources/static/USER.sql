/* CREATE A SEQUENCE FOR ID */
CREATE SEQUENCE USER_ID_SEQ;

/* INSERT INITIAL DATA */
INSERT INTO TB_USERS (ID, NAME, LASTNAME, EMAIL, PHONE)
VALUES (USER_ID_SEQ.nextval, 'JHON', 'DOE', 'jond@gmail.com', '71542988');
INSERT INTO TB_USERS (ID, NAME, LASTNAME, EMAIL, PHONE)
VALUES (USER_ID_SEQ.nextval, 'DAN', 'SMITH', 'dans@gmail.com', '60699412');