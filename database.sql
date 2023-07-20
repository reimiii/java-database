show databases;

create database java_database;

use java_database;

create table customers
(
    id    varchar(100) not null,
    name  varchar(100) not null,
    email varchar(100) not null,
    constraint email_unique unique (email),
    primary key (id)
) engine = innodb;


desc customers;
show tables;

select *
from customers;

create table admin
(
    username varchar(100) not null,
    password varchar(100) not null,
    primary key (username)
) engine = innodb;

insert into admin (username, password)
values ('admin', 'admin');

select *
from admin;

create table comments
(
    id      int not null auto_increment,
    email   varchar(100),
    comment text,
    primary key (id)
) engine = innodb;

select *
from comments;

select count(id)
from comments;

delete
from comments;

create table sample_time
(
    id               int not null auto_increment,
    sample_date      date,
    sample_time      time,
    sample_timestamp timestamp,
    primary key (id)
) engine = innodb;

desc sample_time;

select *
from sample_time;

