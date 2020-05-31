alter table paid_journey drop column paid;

create table thank_size(
    paid_journey_id bigint not null,
    size numeric not null,
    constraint thank_size_pk primary key (paid_journey_id)
);

create table status_thank (
    id bigserial not null,
    name_status varchar(255) not null,
    constraint status_thank_pk primary key (id)
);

alter table paid_journey add column status_thank_id bigint not null;
alter table paid_journey add constraint status_paid_fk foreign key (status_thank_id) references status_thank(id);

alter table thank_size add constraint paid_journey_id_fk foreign key (paid_journey_id) references paid_journey(id);