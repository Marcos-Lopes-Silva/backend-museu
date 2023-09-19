create table tb_pecas(
    id integer not null auto_increment,
    nome varchar(255) not null,
    autor varchar(255) not null,
    curador varchar(255) not null,
    data_adquirida datetime(6),
    descricao_peca varchar(255),
    estado_conservacao varchar(255) not null,
    localizacao varchar(255),
    instituto varchar(255),
    data_devolucao datetime(6),

    primary key(id)
);

create table tb_usuarios(
    id integer not null auto_increment,
    email varchar(255) not null unique,
    senha varchar(255) not null,
    funcionario_id integer not null,

    primary key(id)
);

create table tb_funcionarios(
    id integer not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(255) not null unique,
    telefone varchar(255) not null,
    rg varchar(255) not null unique,
    cargo varchar(255) not null,
    usuario_id integer not null,

    primary key(id)
);

alter table tb_usuarios add foreign key(funcionario_id) references tb_funcionarios(id);
alter table tb_funcionarios add foreign key(usuario_id) references tb_usuarios(id);

