-- Active: 1695110858637@@127.0.0.1@3306@banco

create table
    tb_pecas(
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

primary key(id) );

create table
    tb_usuarios(
        id integer not null auto_increment,
        email varchar(255) not null unique,
        senha varchar(255) not null,
        funcionarios_id integer not null,

primary key(id) );

create table
    tb_funcionarios(
        id integer not null auto_increment,
        nome varchar(255) not null,
        cpf varchar(255) not null unique,
        telefone varchar(255) not null,
        rg varchar(255) not null unique,
        cargo varchar(255) not null,
        usuario_id integer not null,

primary key(id) );

alter table tb_usuarios
add
    foreign key(funcionarios_id) references tb_funcionarios(id);

alter table tb_funcionarios
add
    foreign key(usuario_id) references tb_usuarios(id);

alter table tb_usuarios modify column funcionarios_id integer null;

alter table tb_funcionarios modify column usuario_id integer null;

create table tb_ingressos( 

id integer not null auto_increment,
compra_online bit not null,
data_compra datetime(6) not null,
pagamento varchar(100) not null,
categoria_id integer not null,
funcionarios_id integer,

primary key(id) );

alter table tb_ingressos add ingresso_id integer null;

create table tb_categoria_ingressos( 

id integer not null auto_increment,
nome varchar(255) not null,
preco decimal(10, 2) not null,
ingresso_id integer,
primary key(id)
);

alter table tb_funcionarios add rua varchar(100);

alter table tb_funcionarios add numero varchar(100);

alter table tb_funcionarios add bairro varchar(100);

alter table tb_funcionarios add cidade varchar(100);

alter table tb_funcionarios add estado char;

alter table tb_funcionarios add cep varchar(100);

alter table tb_funcionarios add salario varchar(100);

alter table tb_ingressos
add
    foreign key(categoria_id) references tb_categoria_ingressos(id);

alter table
    tb_categoria_ingressos
add
    foreign key(ingresso_id) references tb_ingressos(id);

alter table tb_categoria_ingressos modify column preco float(53);

alter table tb_ingressos modify column data_compra date;