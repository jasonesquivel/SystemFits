/*
 * ER/Studio Data Architect SQL Code Generation
 * Project :      basededatosmoviles.DM1
 *
 * Date Created : Sunday, May 14, 2023 14:20:45
 * Target DBMS : Microsoft SQL Server 2017
 */

/* 
 * TABLE: Consumo 
 */

CREATE TABLE Consumo(
    IdConsumo            int             NOT NULL,
    ConsumoDeCalorias    int             NULL,
    MetaDeCalorias       int             NULL,
    NombreDeComida       nvarchar(70)    NOT NULL,
    HoraDeComida         time(7)         NULL,
    Id                   int             NOT NULL,
    CONSTRAINT PK5 PRIMARY KEY NONCLUSTERED (IdConsumo)
)
go



IF OBJECT_ID('Consumo') IS NOT NULL
    PRINT '<<< CREATED TABLE Consumo >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Consumo >>>'
go

/* 
 * TABLE: Ejercicios 
 */

CREATE TABLE Ejercicios(
    IdEjercicios        int             NOT NULL,
    NombreEjercicios    nvarchar(100)    NULL,
    SetsEJercicios      int             NOT NULL,
    RepsEjercicio       int        NULL,
    Id                  int             NOT NULL,
    CONSTRAINT PK6 PRIMARY KEY NONCLUSTERED (IdEjercicios)
)
go



IF OBJECT_ID('Ejercicios') IS NOT NULL
    PRINT '<<< CREATED TABLE Ejercicios >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Ejercicios >>>'
go

/* 
 * TABLE: Ingredientes 
 */

CREATE TABLE Ingredientes(
    IdIngredientes    int             NOT NULL,
    Nombre            nvarchar(70)    NULL,
    Calorias          int             NULL,
    Peso              float           NULL,
    Tipo              int             NULL,
    IdRecetas         int             NOT NULL,
    CONSTRAINT PK4 PRIMARY KEY NONCLUSTERED (IdIngredientes)
)
go



IF OBJECT_ID('Ingredientes') IS NOT NULL
    PRINT '<<< CREATED TABLE Ingredientes >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Ingredientes >>>'
go

/* 
 * TABLE: Medidas 
 */

CREATE TABLE Medidas(
    IdMedidas        int             NOT NULL,
    NobreMedida      nvarchar(50)    NULL,
    TamanioMedida    float           NOT NULL,
    Id               int             NOT NULL,
    CONSTRAINT PK7 PRIMARY KEY NONCLUSTERED (IdMedidas)
)
go



IF OBJECT_ID('Medidas') IS NOT NULL
    PRINT '<<< CREATED TABLE Medidas >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Medidas >>>'
go

/* 
 * TABLE: Recetas 
 */

CREATE TABLE Recetas(
    IdRecetas      int              NOT NULL,
    Nombre         nvarchar(70)     NOT NULL,
    Descripcion    nvarchar(150)    NULL,
    Id             int              NOT NULL,
    CONSTRAINT PK3 PRIMARY KEY NONCLUSTERED (IdRecetas)
)
go



IF OBJECT_ID('Recetas') IS NOT NULL
    PRINT '<<< CREATED TABLE Recetas >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Recetas >>>'
go

/* 
 * TABLE: Usuario 
 */

CREATE TABLE Usuario(
    Id             int              NOT NULL,
    FechaNac       datetime         NOT NULL,
    Apellido       nvarchar(50)     NULL,
    Sexo           int              NULL,
    Peso           float            NULL,
    Altura         float            NULL,
    Usuario        nvarchar(60)     NOT NULL,
    Contrasenia    nvarchar(100)    NOT NULL,
    Nombre         nvarchar(100)    NULL,
    Correo         nvarchar(100)    NULL,
    CONSTRAINT PK1 PRIMARY KEY NONCLUSTERED (Id)
)
go



IF OBJECT_ID('Usuario') IS NOT NULL
    PRINT '<<< CREATED TABLE Usuario >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Usuario >>>'
go

/* 
 * TABLE: Consumo 
 */

ALTER TABLE Consumo ADD CONSTRAINT RefUsuario23 
    FOREIGN KEY (Id)
    REFERENCES Usuario(Id)
go


/* 
 * TABLE: Ejercicios 
 */

ALTER TABLE Ejercicios ADD CONSTRAINT RefUsuario24 
    FOREIGN KEY (Id)
    REFERENCES Usuario(Id)
go


/* 
 * TABLE: Ingredientes 
 */

ALTER TABLE Ingredientes ADD CONSTRAINT RefRecetas20 
    FOREIGN KEY (IdRecetas)
    REFERENCES Recetas(IdRecetas)
go


/* 
 * TABLE: Medidas 
 */

ALTER TABLE Medidas ADD CONSTRAINT RefUsuario22 
    FOREIGN KEY (Id)
    REFERENCES Usuario(Id)
go


/* 
 * TABLE: Recetas 
 */

ALTER TABLE Recetas ADD CONSTRAINT RefUsuario21 
    FOREIGN KEY (Id)
    REFERENCES Usuario(Id)
go


