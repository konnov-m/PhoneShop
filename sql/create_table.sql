DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS phone CASCADE;


CREATE TABLE company(
	id bigserial,
	title varchar(256) not null UNIQUE,
	primary key(id)
);


CREATE TABLE phone(
	id bigserial,
	name varchar(256) not null,
	description text,
	company_id bigserial NOT NULL REFERENCES company(id),
	primary key(id)
);