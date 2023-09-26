alter table tb_funcionarios drop foreign key tb_funcionarios_ibfk_3;
alter table tb_viagens_pesquisa drop foreign key tb_viagens_pesquisa_ibfk_1;
drop table tb_pesquisador;

alter table tb_funcionarios add area_especializacao varchar(255);
alter table tb_funcionarios add viagens_pesquisa_id integer;
alter table tb_funcionarios add foreign key(viagens_pesquisa_id) references tb_viagens_pesquisa(id);
alter table tb_viagens_pesquisa add foreign key(pesquisador_id) references tb_funcionarios(id);
alter table tb_funcionarios add role varchar(255);
alter table tb_funcionarios drop column cargo;
alter table tb_funcionarios add demitido bit;
