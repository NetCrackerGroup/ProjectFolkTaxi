create table public.paid_journey (
    id BIGSERIAL NOT NULL,
    journey_id BIGINT NOT NULL,
    app_user_id BIGINT NOT NULL,
    paid BOOLEAN NOT NULL default false,
    constraint paid_journey_pk primary key (id)
);

alter table paid_journey add constraint journey_id_fk foreign key (journey_id) references journeys(journey_id);
alter table paid_journey add constraint app_user_id_fk foreign key (app_user_id) references app_users(user_id);