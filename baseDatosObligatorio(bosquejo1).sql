CREATE DATABASE obligatorioBD;
USE obligatorioBD;

-- Tablas obligatorias

CREATE TABLE Login (
    LogId VARCHAR(20) PRIMARY KEY NOT NULL,
    Contra VARCHAR(20) NOT NULL
);

CREATE TABLE Funcionario (
    Ci VARCHAR(20) PRIMARY KEY NOT NULL,
    Nombre VARCHAR(20) NOT NULL,
    Fch_Nacimiento DATE NOT NULL,
    Dirección VARCHAR(50) NOT NULL ,
    Teléfono VARCHAR(10) NOT NULL ,
    Email VARCHAR(50) NOT NULL ,
    LogId VARCHAR(20) NOT NULL ,
    FOREIGN KEY (LogId) REFERENCES Login(LogId)
);

CREATE TABLE Agenda (
    Nro INT NOT NULL ,
    Ci VARCHAR(20) PRIMARY KEY NOT NULL ,
    Fch_Agenda DATE NOT NULL ,
    FOREIGN KEY (Ci) REFERENCES Funcionario(Ci)
);

CREATE TABLE Carnet_Salud (
    Ci VARCHAR(50) PRIMARY KEY NOT NULL,
    Fch_Emision DATE NOT NULL NOT NULL,
    Fch_Vencimiento DATE NOT NULL,
    Comprobante VARCHAR(255) NOT NULL
);

CREATE TABLE Periodos_Actualizacion (
    Año INT NOT NULL ,
    Semestre INT NOT NULL ,
    Fch_Inicio DATE NOT NULL ,
    Fch_Fin DATE NOT NULL
);

-- Tablas adicionales

CREATE TABLE Estado (
    Ci VARCHAR(50) PRIMARY KEY NOT NULL ,
    Estado BOOLEAN DEFAULT false NOT NULL,
    FOREIGN KEY (Ci) REFERENCES Funcionario(Ci)
);

-- Agregamos ejemplos a cada tabla