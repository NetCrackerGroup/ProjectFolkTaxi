ALTER TABLE schedules
    ADD start_day date;

ALTER TABLE routes
    drop column start_day;