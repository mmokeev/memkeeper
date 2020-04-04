--liquibase formatted sql

--changeset Mokeev_maksim:ddl-init dbms:postgresql
create table simple_table (
  id      bigint primary key,
  user_id varchar(255) not null,
  title   varchar(255)
);

create sequence simple_table_pk_seq increment by 50;