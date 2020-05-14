alter table comentario add constraint fk_comentario_ordem_servico
foreign key (os_id) references ordem_servico (id);