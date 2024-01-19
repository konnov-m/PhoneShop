DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS phone;


CREATE TABLE company(
	id bigserial,
	title varchar(256) not null UNIQUE,
	primary key(id)
);


CREATE TABLE phone(
	id bigserial,
	name varchar(256) not null,
	description varchar(256),
	company_id bigserial NOT NULL REFERENCES company(id),
	primary key(id)
);