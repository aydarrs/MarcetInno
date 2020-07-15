drop table if exists employee;
drop table if exists product;
drop table if exists shop_order;
drop table if exists shop_user;
drop table if exists shop;
drop table if exists courier;
drop table if exists courier_status;
drop table if exists delivery_method;
drop table if exists order_status;

create table if not exists courier_status (
                                              status_id serial primary key,
                                              status varchar(30)
);
create table if not exists courier (
                                       courier_id serial primary key,
                                       first_name varchar(30),
                                       last_name varchar(30),
                                       courier_status courier_status
);
create table if not exists delivery_method (
                                               method_id serial primary key,
                                               method_name varchar(30)
);

create table if not exists shop (
                                    shop_id serial primary key,
                                    address_id varchar(30),
                                    name varchar(30)
);

create table if not exists order_status (
                                            status_id serial primary key,
                                            status varchar(30)
);

create table if not exists employee (
                                        employee_id serial primary key,
                                        access_id int8,
                                        shop shop,
                                        employee_first_name varchar(30),
                                        employee_last_name varchar(30),
                                        email varchar(50),
                                        employee_phone varchar(20),
                                        job_title varchar(20),
                                        job_number varchar(20),
                                        login varchar(20),
                                        password varchar(20)
);

create table if not exists shop_user (
                                         user_id serial primary key,
                                         user_name varchar(30),
                                         first_name varchar(30),
                                         last_name varchar(30),
                                         age int4,
                                         address varchar(30),
                                         shop shop
);
create table if not exists product (
                                       product_id serial primary key,
                                       shop shop,
                                       article varchar(30),
                                       name varchar(30),
                                       description text,
                                       price float8,
                                       count int4
);
create table if not exists shop_order (
                                          order_id serial primary key,
                                          date timestamptz,
                                          courier courier,
                                          shop_user shop_user,
                                          shop shop,
                                          order_status order_status,
                                          delivery_method delivery_method
);