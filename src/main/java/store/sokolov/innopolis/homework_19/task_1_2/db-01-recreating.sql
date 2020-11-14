-- удаление всех объектов
drop table if exists task;
drop table if exists checked_object;
drop table if exists checklist;
drop table if exists checklist_item;
drop table if exists checklist_item_type;

-- создание необходимых объектов
-- таблица типов элементов чек-листов
create table if not exists checklist_item_type (
    id    serial
  , code  varchar(15) not null
  , name  varchar(30) not null
  , descr text
);

alter table if exists checklist_item_type add constraint chechlist_item_type_id_pk primary key (id);

alter table if exists checklist_item_type add constraint checklist_item_type_code_uk unique (code);

-- таблица элементов чек-листов
create table if not exists checklist_item (
    id      bigserial
  , type_id integer not null
  , name    varchar(30)
  , descr   text
);

alter table if exists checklist_item add constraint checklist_item_id_pk primary key (id);

alter table if exists checklist_item add constraint checklist_item_type_id_fk foreign key (type_id) references checklist_item_type(id);

create index if not exists checklist_item_type_id_idx on checklist_item(type_id);

-- таблица иерархий проверяемых объектов
create table if not exists checked_object (
    id        bigserial
  , parent_id bigint
  , name      varchar(30)
  , descr     text
);

alter table if exists checked_object add constraint checked_object_id_pk primary key (id);

alter table if exists checked_object add constraint checked_object_parent_id_fk foreign key (parent_id) references checked_object(id);

create index if not exists check_object_parent_id_idx on checked_object(parent_id);

-- таблица задач на проверку объектов
create table if not exists task (
    id                bigserial
  , checked_object_id bigint
  , name              varchar(30)
  , object_id         bigint
  , descr             text
);

alter table if exists task add constraint task_id_pk primary key (id);

alter table if exists task add constraint task_checked_object_id_fk foreign key (checked_object_id) references checked_object(id);

create index if not exists task_checked_object_id_idx on task(checked_object_id);
