# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  constraint pk_cliente primary key (id)
);

create table pessoa (
  id                            bigint auto_increment not null,
  constraint pk_pessoa primary key (id)
);

create table projeto (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  cliente_id                    bigint,
  constraint uq_projeto_cliente_id unique (cliente_id),
  constraint pk_projeto primary key (id)
);

create table registro_de_acesso (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  uri                           varchar(255),
  data                          datetime(6),
  constraint pk_registro_de_acesso primary key (id)
);

create table tag (
  id                            bigint auto_increment not null,
  tag                           varchar(255),
  tarefa_id                     bigint,
  constraint pk_tag primary key (id)
);

create table tarefa (
  id                            bigint auto_increment not null,
  titulo                        varchar(255),
  descricao                     varchar(255),
  tipo_id                       bigint,
  responsavel_id                bigint,
  membro_id                     bigint,
  criacao                       date,
  time_tracking_id              bigint,
  projeto_id                    bigint,
  constraint uq_tarefa_tipo_id unique (tipo_id),
  constraint uq_tarefa_responsavel_id unique (responsavel_id),
  constraint uq_tarefa_membro_id unique (membro_id),
  constraint uq_tarefa_time_tracking_id unique (time_tracking_id),
  constraint pk_tarefa primary key (id)
);

create table timeline (
  id                            bigint auto_increment not null,
  instante                      datetime(6),
  transicao                     varchar(10),
  descricao                     varchar(255),
  usuario_parecer_id            bigint,
  time_tracking_id              bigint,
  constraint ck_timeline_transicao check (transicao in ('INICIADO','PAUSADO','REINICIADO','FINALIZADO','INALTERADO')),
  constraint uq_timeline_usuario_parecer_id unique (usuario_parecer_id),
  constraint pk_timeline primary key (id)
);

create table timetracking (
  id                            bigint auto_increment not null,
  constraint pk_timetracking primary key (id)
);

create table tipo_tarefa (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  tempo_estimado                varchar(255),
  constraint pk_tipo_tarefa primary key (id)
);

create table token_da_api (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  codigo                        varchar(255),
  expiracao                     datetime(6),
  constraint uq_token_da_api_usuario_id unique (usuario_id),
  constraint pk_token_da_api primary key (id)
);

create table token_de_cadastro (
  id                            bigint auto_increment not null,
  codigo                        varchar(255),
  usuario_id                    bigint,
  constraint uq_token_de_cadastro_usuario_id unique (usuario_id),
  constraint pk_token_de_cadastro primary key (id)
);

create table usuario (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  status                        varchar(7),
  verificado                    tinyint(1) default 0,
  data_cadastro                 date,
  avatar_url                    varchar(255),
  pessoa_id                     bigint,
  papel                         varchar(11),
  constraint ck_usuario_status check (status in ('ATIVO','INATIVO')),
  constraint ck_usuario_papel check (papel in ('CLIENTE','COLABORADOR','ADMIN')),
  constraint uq_usuario_pessoa_id unique (pessoa_id),
  constraint pk_usuario primary key (id)
);

alter table projeto add constraint fk_projeto_cliente_id foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;

alter table registro_de_acesso add constraint fk_registro_de_acesso_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_registro_de_acesso_usuario_id on registro_de_acesso (usuario_id);

alter table tag add constraint fk_tag_tarefa_id foreign key (tarefa_id) references tarefa (id) on delete restrict on update restrict;
create index ix_tag_tarefa_id on tag (tarefa_id);

alter table tarefa add constraint fk_tarefa_tipo_id foreign key (tipo_id) references tipo_tarefa (id) on delete restrict on update restrict;

alter table tarefa add constraint fk_tarefa_responsavel_id foreign key (responsavel_id) references usuario (id) on delete restrict on update restrict;

alter table tarefa add constraint fk_tarefa_membro_id foreign key (membro_id) references usuario (id) on delete restrict on update restrict;

alter table tarefa add constraint fk_tarefa_time_tracking_id foreign key (time_tracking_id) references timetracking (id) on delete restrict on update restrict;

alter table tarefa add constraint fk_tarefa_projeto_id foreign key (projeto_id) references projeto (id) on delete restrict on update restrict;
create index ix_tarefa_projeto_id on tarefa (projeto_id);

alter table timeline add constraint fk_timeline_usuario_parecer_id foreign key (usuario_parecer_id) references usuario (id) on delete restrict on update restrict;

alter table timeline add constraint fk_timeline_time_tracking_id foreign key (time_tracking_id) references timetracking (id) on delete restrict on update restrict;
create index ix_timeline_time_tracking_id on timeline (time_tracking_id);

alter table token_da_api add constraint fk_token_da_api_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table token_de_cadastro add constraint fk_token_de_cadastro_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table usuario add constraint fk_usuario_pessoa_id foreign key (pessoa_id) references pessoa (id) on delete restrict on update restrict;


# --- !Downs

alter table projeto drop foreign key fk_projeto_cliente_id;

alter table registro_de_acesso drop foreign key fk_registro_de_acesso_usuario_id;
drop index ix_registro_de_acesso_usuario_id on registro_de_acesso;

alter table tag drop foreign key fk_tag_tarefa_id;
drop index ix_tag_tarefa_id on tag;

alter table tarefa drop foreign key fk_tarefa_tipo_id;

alter table tarefa drop foreign key fk_tarefa_responsavel_id;

alter table tarefa drop foreign key fk_tarefa_membro_id;

alter table tarefa drop foreign key fk_tarefa_time_tracking_id;

alter table tarefa drop foreign key fk_tarefa_projeto_id;
drop index ix_tarefa_projeto_id on tarefa;

alter table timeline drop foreign key fk_timeline_usuario_parecer_id;

alter table timeline drop foreign key fk_timeline_time_tracking_id;
drop index ix_timeline_time_tracking_id on timeline;

alter table token_da_api drop foreign key fk_token_da_api_usuario_id;

alter table token_de_cadastro drop foreign key fk_token_de_cadastro_usuario_id;

alter table usuario drop foreign key fk_usuario_pessoa_id;

drop table if exists cliente;

drop table if exists pessoa;

drop table if exists projeto;

drop table if exists registro_de_acesso;

drop table if exists tag;

drop table if exists tarefa;

drop table if exists timeline;

drop table if exists timetracking;

drop table if exists tipo_tarefa;

drop table if exists token_da_api;

drop table if exists token_de_cadastro;

drop table if exists usuario;

