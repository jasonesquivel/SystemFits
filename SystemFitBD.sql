
CREATE DATABASE Systemfitbd;
use Systemfitbd;

CREATE TABLE Usuarios(
    IdUsuario INT NOT NULL AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    FechaNac DATETIME NOT NULL,
    Sexo INT NOT NULL,
    Peso FLOAT NOT NULL,
    Altura FLOAT NOT NULL,
    Usuario VARCHAR(60) NOT NULL,
    Contrasenia VARCHAR(100) NOT NULL,
    PRIMARY KEY(IdUsuario)
) ENGINE = InnoDB; 

CREATE TABLE Comidas(
    IdComida INT NOT NULL AUTO_INCREMENT,
    NombreComida VARCHAR(100) NOT NULL,
    Calorias INT NOT NULL,
    PRIMARY KEY(IdComida) 
) ENGINE = InnoDB; 

CREATE TABLE Ejercicios(
    IdEjercicios INT NOT NULL AUTO_INCREMENT,
    NombreEjercicio VARCHAR(100) NOT NULL,
    SetsEJercicio INT NOT NULL,
    RepsEjercicio INT NOT NULL,
    PRIMARY KEY(IdEjercicios)  
) ENGINE = InnoDB; 

CREATE TABLE Medidas(
    IdMedidas INT NOT NULL AUTO_INCREMENT,
    NombreMedida VARCHAR(50) NOT NULL,
    TamanioMedida FLOAT NOT NULL,
    PRIMARY KEY(IdMedidas)
   
) ENGINE = InnoDB; 

CREATE TABLE Recetas(
    IdRecetas INT NOT NULL AUTO_INCREMENT,
    NombreReceta VARCHAR(70) NOT NULL,
    DescripcionReceta VARCHAR(150) NOT NULL,
    PRIMARY KEY(IdRecetas)
) ENGINE = InnoDB;
