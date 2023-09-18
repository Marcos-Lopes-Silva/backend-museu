create table ingressos(

    id integer not null auto_increment,
    compra_online boolean not null,
    data_compra datetime(6) not null,
    pagamento varchar(100) not null,
    categoria_id integer not null,
    vendedor_id integer,
    visitante_id integer not null,

    primary key(id),
)

create table categorias_ingresso(

    id integer not null auto_increment,
    nome varchar(255) not null,
    preco decimal(10,2) not null,
    ingresso_id integer,
    
    primary key(id),
)

alter table ingressos add foreign key(categoria_id) references categorias_ingresso(id);

alter table categorias_ingresso add foreign key(ingresso_id) references ingressos(id);