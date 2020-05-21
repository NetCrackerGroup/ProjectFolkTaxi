UPDATE app_users
SET passenger_rating = NULL
WHERE fio = 'Ilya Aganin';

CREATE SEQUENCE passenger_rate_history_id_seq;

CREATE TABLE public.Passenger_Rate_History (
    Passenger_Rate_History_ID BIGINT NOT NULL default nextval('passenger_rate_history_id_seq'),
    Journey_ID BIGINT NOT NULL,
    Rater_ID BIGINT NOT NULL,
    Passenger_ID BIGINT NOT NULL,
    Mark BIGINT NOT NULL,
    CONSTRAINT passenger_rate_history_id PRIMARY KEY (Passenger_Rate_History_ID),
    UNIQUE (Journey_ID, Rater_ID, Passenger_ID)
);

ALTER TABLE public.Passenger_Rate_History ADD CONSTRAINT journeys_passenger_rate_history_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.journeys (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_Rate_History ADD CONSTRAINT app_users_passenger_rate_history_fk
FOREIGN KEY (Passenger_ID)
REFERENCES public.app_users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

CREATE SEQUENCE driver_rate_history_id_seq;

CREATE TABLE public.Driver_Rate_History (
    Driver_Rate_History_ID BIGINT NOT NULL default nextval('driver_rate_history_id_seq'),
    Journey_ID BIGINT NOT NULL,
    Rater_ID BIGINT NOT NULL,
    Driver_ID BIGINT NOT NULL,
    Mark BIGINT NOT NULL,
    CONSTRAINT driver_rate_history_id PRIMARY KEY (Driver_Rate_History_ID),
    UNIQUE (Journey_ID, Rater_ID, Driver_ID)
);

ALTER TABLE public.Driver_Rate_History ADD CONSTRAINT journeys_driver_rate_history_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.journeys (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Driver_Rate_History ADD CONSTRAINT app_users_driver_rate_history_fk
FOREIGN KEY (Driver_ID)
REFERENCES public.app_users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;