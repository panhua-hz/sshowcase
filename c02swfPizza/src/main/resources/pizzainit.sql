create table CUSTOMER(
	id identity,
	name varchar(100) not null,
	address varchar(100) not null,
	city varchar(100) not null,
	state varchar(100) not null,
	zipCode varchar(100) not null,
	phoneNumber varchar(100) not null
);

insert into CUSTOMER(name, address, city, state, zipCode, phoneNumber) values('Pan Hua','Bolin Impression 123301','Hangzhou','Zhejiang','310013','13758193654');