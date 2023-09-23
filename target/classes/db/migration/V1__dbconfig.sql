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
    secao_id integer not null,

    primary key(id)
);

create table tb_usuarios(
    id integer not null auto_increment,
    email varchar(255) not null unique,
    senha varchar(255) not null,
    funcionarios_id integer,

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
    rua varchar(255) not null,
    numero varchar(255) not null,
    bairro varchar(255) not null,
    cidade varchar(255) not null,
    estado varchar(2) not null,
    cep varchar(255) not null,
    salario float not null,

    primary key(id)
);

alter table tb_usuarios add foreign key(funcionarios_id) references tb_funcionarios(id);
alter table tb_funcionarios add foreign key(usuario_id) references tb_usuarios(id);



create table tb_ingressos(

    id integer not null auto_increment,
    compra_online bit not null,
    data_compra date not null,
    pagamento varchar(100) not null,
    doc_visitante varchar(255) not null,
    categoria_id integer not null,
    vendedor_id integer,

    primary key(id)
);

create table tb_categoria_ingressos(

    id integer not null auto_increment,
    nome varchar(255) not null,
    preco float(53) not null,
    ingresso_id integer,
    
    primary key(id)
);





alter table tb_ingressos add foreign key(categoria_id) references tb_categoria_ingressos(id);

alter table tb_categoria_ingressos add foreign key(ingresso_id) references tb_ingressos(id);

alter table tb_ingressos modify column data_compra date;
