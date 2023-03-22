CREATE TABLE usuarios(
       id uuid not null primary key,
       login varchar(100) not null,
       password varchar(100) not null
);