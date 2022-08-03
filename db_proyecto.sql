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

insert into persona(nombre,apellido,direccion,correo,telefono,genero) 
values('jorge armando','sandoval latin','villa nueva guatemala','jorge@correo.com','12345678','M');
select * from persona;

insert into usuario(codigo_persona,usuario,password,fecha_creacion,role,estado) 
values(1,'jsandoval','123456','2022-08-01',1,true);
select * from usuario;