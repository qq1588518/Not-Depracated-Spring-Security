package com.example.withauthmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WithAuthManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WithAuthManagerApplication.class, args);
    }
}


/*
drop table if exists roles;
create table roles (
	id int primary key AUTO_INCREMENT,
    name varchar(50) not null
);

drop table if exists authorities;
create table authorities (
	id int primary key AUTO_INCREMENT,
    name varchar(50) not null
);

drop table if exists users;
create table users (
	id int primary key AUTO_INCREMENT,
    email varchar(255) not null,
    password varchar(255) not null,

    role_id int not null default 1,
    FOREIGN KEY(role_id) references roles(id) ON UPDATE CASCADE on delete cascade
);
 */