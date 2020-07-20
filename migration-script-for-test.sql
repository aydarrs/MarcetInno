drop table if exists shop;
drop table if exists product;
drop table if exists courier;
drop table if exists user_account;
drop table if exists order_shop;

create table if not exists shop (
                                    shop_id serial primary key,
                                    address varchar(30),
                                    name varchar(30)
);

create table if not exists product (
                                       product_id serial primary key,
                                       shop_id int4 REFERENCES shop (shop_id),
                                       article varchar(30),
                                       name varchar(30),
                                       description text,
                                       price float8,
                                       count int4
);

create table if not exists courier (
                                       courier_id serial primary key,
                                       first_name varchar(30),
                                       last_name varchar(30),
                                       delivery_method varchar(30)
);
create table if not exists user_account (
                                         user_id serial primary key,
                                         user_type varchar(30),
                                         user_name varchar(30),
                                         first_name varchar(30),
                                         last_name varchar(30)
);
create table if not exists order_shop (
                                          order_id serial primary key,
                                          order_status varchar(30),
                                          date timestamptz,
                                          user_id int4 REFERENCES user_account (user_id),
                                          product_id int4 REFERENCES product (product_id),
                                          courier_id int4 REFERENCES courier (courier_id)
);