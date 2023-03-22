create table usuario(
    id bigInt not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

    insert into usuario values(1, 'Joao Victor', 'joao@gmail.com')