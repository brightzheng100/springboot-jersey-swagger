CREATE TABLE PERSON (
	id integer not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
   	primary key(id)
);

insert into PERSON (id, first_name, last_name) values (1, 'Bright', 'Zheng');

-- alter table student add column remarks varchar(200) null;