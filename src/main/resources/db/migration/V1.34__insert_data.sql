UPDATE Passenger_Rating SET Average_Mark = 0;
ALTER TABLE Passenger_Rating ADD Number_Of_Votes NUMERIC DEFAULT 0;
INSERT INTO Passenger_In_Journey(Journey_Id, User_Id) VALUES(6, 7);

CREATE SEQUENCE rate_history_id_seq;

CREATE TABLE public.Rate_History (
    Rate_History_ID BIGINT NOT NULL default nextval('rate_history_id_seq'),
    Journey_ID BIGINT NOT NULL,
    Rater_ID BIGINT NOT NULL,
    User_ID BIGINT NOT NULL,
    Mark BIGINT NOT NULL,
    User_Is_Driver BOOLEAN NOT NULL,
    CONSTRAINT rate_history_id PRIMARY KEY (Rate_History_ID),
    UNIQUE (Journey_ID, Rater_ID, User_ID, User_Is_Driver)
);

ALTER TABLE public.Rate_History ADD CONSTRAINT journeys_rate_history_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.journeys (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Rate_History ADD CONSTRAINT app_users_rate_history_fk
FOREIGN KEY (User_ID)
REFERENCES public.app_users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;