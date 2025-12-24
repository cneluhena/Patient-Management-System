create table if not exists tbl_doctor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(100) not null

);

CREATE SEQUENCE tbl_specialization_seq
    START 1
    INCREMENT 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table if not exists tbl_specialization (
    id bigint primary key DEFAULT nextval('tbl_specialization_seq'),
    name varchar(100) not null unique

);

create table if not exists tbl_doctor_specialization_mapping (
    doctor_id UUID not null,
    specialization_id bigint not null,
    primary key (doctor_id, specialization_id),
    foreign key (doctor_id) references tbl_doctor(id) on delete cascade,
    foreign key (specialization_id) references tbl_specialization(id) on delete cascade
);

insert into tbl_specialization(name) values ('Cardiologists'), ('Neurologists')