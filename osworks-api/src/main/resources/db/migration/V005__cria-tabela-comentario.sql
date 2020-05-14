create table if not exists comentario (
	id bigint not null auto_increment,
	os_id bigint not null,
	descricao text not null,
	data_envio datetime not null,
	
	primary key (id)

);