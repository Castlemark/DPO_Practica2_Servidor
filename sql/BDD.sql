
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

CREATE TABLE Partida(
	id_partida BIGINT UNSIGNED AUTO_INCREMENT,
	data_partida DATE,
    PRIMARY KEY (id_partida)
);

CREATE TABLE Usuari_partida(
	id_jugador VARCHAR(255),
    id_partida BIGINT UNSIGNED,
    puntuacio INT,
    FOREIGN KEY (id_jugador) REFERENCES Usuari(login),
    FOREIGN KEY (id_partida) REFERENCES Partida(id_partida)
);



    
    
