alter table tb_usuarios add role_id integer;
alter table tb_usuarios add foreign key (role_id) references tb_role(id);
alter table tb_role add foreign key (usuario) references tb_usuarios(id);
