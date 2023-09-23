alter table tb_ingressos add foreign key(vendedor_id) references tb_funcionarios(id);
alter table tb_funcionarios add ingressos integer;
alter table tb_funcionarios add foreign key(ingressos) references tb_ingressos(id);

create table tb_pesquisador(
    id integer not null auto_increment,
    area_especializacao varchar(255) not null,
    viagens_pesquisa_id integer,
    funcionarios_id integer,

    primary key(id)
);


create table tb_secao(

    id integer not null auto_increment,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    pecas_id integer,
    divisao_id integer not null,


    primary key(id)
);

create table tb_divisao(
    id integer not null auto_increment,
    nome varchar(255) not null,
    predio varchar(255) not null,
    sala varchar(255) not null,
    secao_id integer,

    primary key(id)
);

create table tb_viagens_pesquisa(
    id integer not null auto_increment,
    data_inicio datetime(6) not null,
    data_fim datetime(6) not null,
    aprovada bit,
    destino varchar(255) not null,
    pesquisador_id integer not null,
    resultados varchar(255),
    objetivo varchar(255) not null,
    custos float not null,

    primary key(id)
);

alter table tb_funcionarios add pesquisador_id integer;
alter table tb_pesquisador add foreign key(funcionarios_id) references tb_funcionarios(id);
alter table tb_pesquisador add foreign key(viagens_pesquisa_id) references tb_viagens_pesquisa(id);
alter table tb_funcionarios add foreign key(pesquisador_id) references tb_pesquisador(id);
alter table tb_secao add foreign key(pecas_id) references tb_pecas(id);
alter table tb_secao add foreign key(divisao_id) references tb_divisao(id);
alter table tb_divisao add foreign key(secao_id) references tb_secao(id);
alter table tb_viagens_pesquisa add foreign key(pesquisador_id) references tb_pesquisador(id);
alter table tb_ingressos add ingresso_id integer;