create table tb_role_usuario (
    usuario_id integer not null,
    role_id integer not null,
    primary key (usuario_id, role_id),
    KEY role_id (role_id),
  CONSTRAINT tb_role_usuario_ibfk_1 
   FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id),
  CONSTRAINT tb_role_usuario_ibfk_2 
   FOREIGN KEY (role_id) REFERENCES tb_role (id)
);

