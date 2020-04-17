ALTER TABLE App_Users ADD Info VARCHAR;
ALTER SEQUENCE user_id_seq RESTART WITH 100;


ALTER TABLE routes
ADD count_of_places BIGINT NOT NULL default 1;

ALTER TABLE schedules
    ADD schedule_day BIGINT;

ALTER TABLE schedules
    ADD start_day date;
