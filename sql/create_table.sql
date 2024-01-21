DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS display CASCADE;
DROP TABLE IF EXISTS phone;


CREATE TABLE company(
	id bigserial,
	title varchar(256) not null UNIQUE,
	primary key(id)
);


CREATE TABLE display(
	id bigserial,
	name varchar(256),
	diagonal numeric(3,1),
	resolutionX smallint,
	resolutionY smallint,
	type_matrix varchar(32),
	rate smallint,
	primary key(id)
);


CREATE TABLE phone(
	id bigserial,
	name varchar(256) not null,
	description text,
	company_id bigserial NOT NULL REFERENCES company(id),
	display_id bigserial NOT NULL REFERENCES display(id),
	primary key(id)
);