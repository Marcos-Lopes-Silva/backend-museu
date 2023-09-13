create table usuario (
    id bigint not null auto_increment,
    email varchar(50) not null,
    senha varchar(100) not null,
    funcionario_id bigint,

    primary key (
        id
    )
);

create table funcionario (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(100) not null,
    telefone varchar(100) not null,
    rg varchar(100) not null,
    cargo varchar(100) not null,
    usuario_id bigint,

    primary key (
        id
    )
    
);

alter table usuario add foreign key (funcionario_id) references funcionario(id);
alter table funcionario add foreign key (usuario_id) references usuario(id);


create table peca (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    autor varchar(100) not null,
    curador varchar(100) not null,
    data_adquirida date not null,
    descricao_peca varchar(100) not null,
    
    primary key(id)
);