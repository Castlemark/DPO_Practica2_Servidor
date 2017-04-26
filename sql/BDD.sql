CREATE DATABASE Troner;
USE Troner;

CREATE TABLE Usuari(
  id_jugador BIGINT UNSIGNED AUTO_INCREMENT,
  login VARCHAR(511),
  mail VARCHAR(511),
  contrasenya VARCHAR(511),
  punts INT,
  data_registre DATE,
  data_ultimacces DATE,
  PRIMARY KEY (id_jugador)
);

CREATE TABLE Partida(
  id_partida BIGINT UNSIGNED AUTO_INCREMENT,
  data_partida DATE,
  num_jugadors INT,
  PRIMARY KEY (id_partida)
);

CREATE TABLE Usuari_Partida(
  id_jugador BIGINT UNSIGNED,
  id_partida BIGINT UNSIGNED,
  puntuacio INT,
  FOREIGN KEY (id_jugador) REFERENCES Usuari(id_jugador),
  FOREIGN KEY (id_partida) REFERENCES Partida(id_partida)
);

CREATE TABLE Torneig(
  id_torneig BIGINT UNSIGNED AUTO_INCREMENT,
  data_torneig DATE,
  PRIMARY KEY (id_torneig)
);

CREATE TABLE Torneig_Partida(
  id_torneig BIGINT UNSIGNED,
  id_partida BIGINT UNSIGNED
);