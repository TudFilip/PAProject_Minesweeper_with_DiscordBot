DROP TABLE USERI CASCADE CONSTRAINTS
/
DROP TABLE USERI_LOGGATI CASCADE CONSTRAINTS
/
DROP TABLE SCORURI CASCADE CONSTRAINTS
/

CREATE TABLE USERI (
   id INTEGER PRIMARY KEY,
   username VARCHAR2(50) UNIQUE NOT NULL
)
/

CREATE TABLE USERI_LOGGATI (
   id INTEGER PRIMARY KEY,
   id_user INTEGER NOT NULL,
   CONSTRAINT fk_useri_loggati_id_user FOREIGN KEY (id_user) REFERENCES useri(id)
)
/

CREATE TABLE SCORURI (
     id integer PRIMARY KEY,
     id_user INTEGER NOT NULL,
     scor INTEGER NOT NULL,
     data_scor DATE NOT NULL,
     CONSTRAINT fk_scoruri_id_user FOREIGN KEY (id_user) REFERENCES useri(id)
)
/

DROP SEQUENCE useri_sequence
/
DROP SEQUENCE USERI_LOGGATI_SEQUENCE
/
DROP SEQUENCE scoruri_sequence
/

CREATE SEQUENCE useri_sequence START WITH 1 INCREMENT BY 1
/
CREATE SEQUENCE useri_loggati_sequence START WITH 1 INCREMENT BY 1
/
CREATE SEQUENCE scoruri_sequence START WITH 1 INCREMENT BY 1
/

CREATE OR REPLACE TRIGGER adauga_nou_user
    before insert on useri
    for each row
BEGIN
    :new.id := useri_sequence.nextval;
END;
/

CREATE OR REPLACE TRIGGER adauga_user_loggat
    before insert on useri_loggati
    for each row
BEGIN
    :new.id := useri_loggati_sequence.nextval;
END;
/

CREATE OR REPLACE TRIGGER adauga_scor
    before insert on scoruri
    for each row
BEGIN
    :new.id := scoruri_sequence.nextval;
END;
/
