create table if not exists dev.parameter
(
	parameter_nr bigserial,
	parameter_no varchar(4000) not null,
	name varchar(4000) not null,
	parameter_type_uid bigint not null,
	number_value numeric(19,5),
	boolean_value boolean,
	time_value timestamp,
	string_value varchar(4000),
	binary_value bytea,
	status_uid bigint default 101 not null,
	smart_value bigint
);

alter table dev.parameter owner to postgres;

create table if not exists dev.uc
(
	uc_uid bigserial,
	code_type bigint not null,
	ext_key varchar(4000),
	value double precision,
	icon_id varchar(255),
	tool_tip varchar(4000),
	foreground_color varchar(255),
	background_color varchar(255),
	font varchar(255),
	parent_uid bigint,
	is_builtin boolean default false,
	status_uid bigint default 101 not null
);

alter table dev.uc owner to postgres;

create table if not exists dev.uc_text
(
	uc_uid bigint not null,
	language_uid bigint not null,
	text text not null
);

alter table dev.uc_text owner to postgres;

