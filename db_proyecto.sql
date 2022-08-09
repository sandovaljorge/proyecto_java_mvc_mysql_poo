create database db_proyecto;
use db_proyecto;

/*Creacion de objetos en sql*/
create table persona(
	codigo int primary key auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    direccion varchar(200) not null,
    correo varchar(50) not null,
    telefono varchar(15) not null,
    genero char(1) 
);
/*alter table persona add genero char(1) null;*/
alter table persona auto_increment=1;

create table usuario(
	codigo int primary key auto_increment,
    codigo_persona int not null unique,
    usuario varchar(50) not null,
    password varchar(250) not null,
    fecha_creacion date,
    role smallint not null,
    estado boolean null,
    constraint fk_usuario_persona foreign key(codigo_persona) 
    references persona(codigo) on delete cascade on update cascade
);
alter table usuario auto_increment=1;

CREATE TABLE SUCURSAL(
	CODIGO SMALLINT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(100) NOT NULL,
    DIRECCION VARCHAR(250) NOT NULL,
    CORREO VARCHAR(50) NOT NULL,
    TELEFONO VARCHAR(15)
);
ALTER TABLE SUCURSAL AUTO_INCREMENT=1;

CREATE TABLE PRODUCTO(
	CODIGO INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    DESCRIPCION VARCHAR(250) NOT NULL,
    CANTIDAD INT NOT NULL,
    PRECIO FLOAT NOT NULL
);
ALTER TABLE PRODUCTO AUTO_INCREMENT=1;

CREATE TABLE CLIENTE(
	CODIGO INT PRIMARY KEY AUTO_INCREMENT,
    CODIGO_PERSONA INT NOT NULL UNIQUE,
    NIT VARCHAR(20) NOT NULL,
    CONSTRAINT FK_CLIENTE_PERSONA FOREIGN KEY(CODIGO_PERSONA) 
    REFERENCES PERSONA(CODIGO) ON DELETE CASCADE ON UPDATE CASCADE
);
ALTER TABLE CLIENTE AUTO_INCREMENT=1;
SELECT * FROM CLIENTE;
SELECT C.CODIGO,P.NOMBRE,NIT,P.CORREO,P.GENERO FROM CLIENTE AS C 
INNER JOIN PERSONA AS P ON C.CODIGO_PERSONA=P.CODIGO;

select * from usuario;


INSERT INTO PRODUCTO(NOMBRE,DESCRIPCION, CANTIDAD,PRECIO) 
VALUES('LECHE','LACTEOS',20,70.5);

insert into sucursal(nombre,direccion,correo,telefono) values 
('Walmart','Zona 1','walmert','12345678');

insert into persona(nombre,apellido,direccion,correo,telefono,genero) 
values('jorge armando','sandoval latin','villa nueva guatemala','jorge@correo.com','12345678','M');
select * from persona;

insert into usuario(codigo_persona,usuario,password,fecha_creacion,role,estado) 
values(10,'sFuentes','123456','2022-08-08',2,true);
select * from producto;

select * from sucursal;