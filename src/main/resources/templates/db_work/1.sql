-- создаем таблицы в бд

create table ruler (
    id bigint not null auto_increment,
    name varchar(255) not null,
    age int not null,
    primary key (id));

create table planet (
    id bigint not null auto_increment,
    name varchar(255) not null,
    ruler_id bigint not null,
    primary key (id),
    foreign key (ruler_id) REFERENCES ruler(id));

create table ruler_of_planet (
id bigint not null auto_increment,
    ruler_id bigint,
    planet_id bigint,
    primary key (id),
    foreign key (ruler_id) REFERENCES ruler(id),
    foreign key (planet_id) REFERENCES planet(id));
