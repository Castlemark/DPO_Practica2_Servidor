
CREATE DATABASE Troner;
USE Troner;

CREATE TABLE Usuari(
	login VARCHAR(511),
    mail VARCHAR(511),
    contrasenya VARCHAR(511),
    punts INT,
    data_registre DATE,
    data_ultimacces DATE,
    PRIMARY KEY (login, mail)
);


    
    
