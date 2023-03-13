CREATE DATABASE order_magament;

USE order_managment;

CREATE TABLE product_line(
	product_line bigint primary key identity(1, 1),
	text_decription text,
	html_description text,
	image_path varchar(255),
	"image" binary null,
);

CREATE TABLE product(
	product bigint primary key identity(1, 1),
	product_name varchar(255),
	product_line bigint,
	product_scale varchar(255),
	product_vendor varchar(255),
	text_decription text,
	html_description text,
	image_path varchar(255),
	"image" binary null,
);

