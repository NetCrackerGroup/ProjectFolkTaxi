
ALTER TABLE routes
ADD count_of_places BIGINT NOT NULL default 1;

ALTER TABLE schedules
    ADD schedule_day BIGINT;

ALTER TABLE routes
    ADD start_day date;
