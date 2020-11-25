create sequence hibernate_sequence start with 1 increment by 1;

create table movie_ticket (
    id bigint not null,
    movie_id varchar(255),
    number_of_Visitors integer not null check (number_of_Visitors>=0),
    user_user_id varchar(255) not null,
    primary key (id));

create table users (
    user_id varchar(255) not null,
    user_email varchar(255) not null,
    cash integer not null check (cash>=0),
    primary key (user_id));

alter table movie_ticket add constraint FKtco9dei78cocpwi1sxye9mw3b foreign key (user_user_id) references users;
