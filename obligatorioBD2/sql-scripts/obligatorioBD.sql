CREATE TABLE Administracion (
    LogId VARCHAR(20) PRIMARY KEY NOT NULL,
    Rol BOOLEAN NOT NULL,
    UNIQUE (LogId)
);

CREATE TABLE Login (
    LogId VARCHAR(20) PRIMARY KEY NOT NULL,
    Contra VARCHAR(50) NOT NULL,
    FOREIGN KEY (LogId) REFERENCES Administracion(LogId)
);

CREATE TABLE Funcionario (
    Ci INT PRIMARY KEY NOT NULL,
    Nombre VARCHAR(20) NOT NULL,
    Apellido VARCHAR(20) NOT NULL,
    Fch_Nacimiento DATE NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    Telefono VARCHAR(10) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    LogId VARCHAR(20) NOT NULL,
    UNIQUE (Ci),
    UNIQUE (Telefono),
    UNIQUE (Email),
    FOREIGN KEY (LogId) REFERENCES Login(LogId)
);

CREATE TABLE Agenda (
    Nro INT AUTO_INCREMENT PRIMARY KEY,
    Ci INT NOT NULL,
    Fch_Agenda DATE NOT NULL
);

CREATE TABLE Carnet_Salud (
    Ci INT NOT NULL ,
    Fch_Emision DATE NOT NULL NOT NULL,
    Fch_Vencimiento DATE NOT NULL,
    Comprobante VARCHAR(255) NOT NULL,
    FOREIGN KEY (Ci) REFERENCES Funcionario(Ci)
);

CREATE TABLE Periodos_Actualizacion (
    Nro INT AUTO_INCREMENT PRIMARY KEY,
    Anio INT NOT NULL,
    Semestre INT NOT NULL,
    Fch_Inicio DATE NOT NULL,
    Fch_Fin DATE NOT NULL
);

CREATE TABLE Ultimo_Inicio (
    Nro INT AUTO_INCREMENT PRIMARY KEY,
    LogId VARCHAR(20) NOT NULL,
    FOREIGN KEY (LogId) REFERENCES Login(LogId)
);

-- Inserts OBLIGATORIOS

-- Administrador
INSERT INTO Administracion(LogId, Rol) VALUES ('admin',true);
INSERT INTO Login(LogId, Contra) VALUES ('admin','admin');

-- Periodo para poder hacer el formulario
INSERT INTO Periodos_Actualizacion(Anio, Semestre, Fch_Inicio, Fch_Fin) VALUES (2023,2,'2023-11-01','2023-11-15');

-- El siguiente insert es para testear la parte de completar datos y el envío de mails, cambiar el mail por uno al cual quieran que se le llegue la notificación de que su carnet está vencido y debe completar el formulario
INSERT INTO Administracion(LogId, Rol) VALUES('probar12345',false);
INSERT INTO Login(LogId, Contra) VALUES('probar12345','80aabc33aab7be71c25e2215a93752a1'); -- contraseña: probar12345
INSERT INTO Funcionario(Ci, Nombre, Apellido, Fch_Nacimiento, Direccion, Telefono, Email, LogId) VALUES (33333333,'Prueba','Test','2000-12-12','Los aromos','099991891','albinbruno0020@gmail.com','probar12345');
INSERT INTO Carnet_Salud(Ci, Fch_Emision, Fch_Vencimiento, Comprobante) VALUES (33333333,'2022-10-10','2023-10-10','rutaArchivo');

-- Este inters es para testear que se agendo con exito para tal fecha (futura) y la idea es que le envie un mail avisandole que quedo agendado (cambiar el mail x uno el cual quiera q se llegue q no sea igual al anterior test)
INSERT INTO Administracion(LogId, Rol) VALUES('2probar12345',false);
INSERT INTO Login(LogId, Contra) VALUES('2probar12345','0d5492f68a17a0abc3f54e6e948dcbed'); -- contraseña: 2probar12345
INSERT INTO Funcionario(Ci, Nombre, Apellido, Fch_Nacimiento, Direccion, Telefono, Email, LogId) VALUES (22222222,'Pruebados','Testdos','2000-12-12','La blanqueada','099991899','borgesabilene12@gmail.com','2probar12345');
INSERT INTO Agenda(Ci, Fch_Agenda) VALUES (22222222,'2024-10-10');