CREATE TABLE public.Passenger_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT passenger_rating_pk PRIMARY KEY (User_ID)
);

CREATE TABLE public.Moderator (
                Moderator_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                CONSTRAINT moderator_pk PRIMARY KEY (Moderator_ID)
);



CREATE TABLE public.Driver_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT driver_rating_pk PRIMARY KEY (User_ID)
);



CREATE SEQUENCE group_id_seq;

CREATE TABLE public.Group_1 (
                Group_ID BIGINT NOT NULL default nextval('group_id_seq'),
                Group_Name VARCHAR NOT NULL,
                Link VARCHAR NOT NULL,
                CONSTRAINT group_1_pk PRIMARY KEY (Group_ID)
);

CREATE SEQUENCE city_id_seq;

CREATE TABLE public.City (
                City_ID BIGINT NOT NULL default nextval('city_id_seq'),
                City_Name VARCHAR NOT NULL,
                City_Map VARCHAR NOT NULL,
                CONSTRAINT city_pk PRIMARY KEY (City_ID)
);


CREATE SEQUENCE user_id_seq
start with 7;

CREATE TYPE Role_names AS ENUM ( 'ROLE_ADMIN', 'ROLE_USER');
CREATE TABLE public.User_1 (
                User_ID BIGINT NOT NULL default nextval('user_id_seq'),
                City_ID BIGINT NOT NULL,
                Role_name VARCHAR NOT NULL,
                FIO VARCHAR NOT NULL,
                Driver_Rating DOUBLE PRECISION,
                Passenger_Rating DOUBLE PRECISION,
                Email VARCHAR,
                Phone_Number VARCHAR,
                Password VARCHAR,
                CONSTRAINT user_id PRIMARY KEY (User_ID)
);



CREATE SEQUENCE review_id_seq;

CREATE TABLE public.Review (
                Review_ID BIGINT NOT NULL default nextval('review_id_seq'),
                Is_passenger BOOLEAN NOT NULL,
                Mark INTEGER NOT NULL,
                Additional_Text VARCHAR,
                User_ID BIGINT NOT NULL,
                CONSTRAINT review_pk PRIMARY KEY (Review_ID)
);

CREATE SEQUENCE notification_id_seq;
CREATE TABLE public.Notification (
                Notification_ID BIGINT NOT NULL default nextval('notification_id_seq'),
                Text VARCHAR NOT NULL,
                Delivery_Channel VARCHAR NOT NULL,
                Was_Watched BOOLEAN NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT notification_pk PRIMARY KEY (Notification_ID)
);

CREATE SEQUENCE report_id_seq;
CREATE TABLE public.Report (
                Report_ID BIGINT NOT NULL default nextval('report_id_seq'),
                Report_Text VARCHAR,
                Report_Reason VARCHAR NOT NULL,
                Was_Considered BOOLEAN NOT NULL,
                Moderator_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT report_pk PRIMARY KEY (Report_ID)
);


CREATE TABLE public.User_In_Group (
                Group_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT user_in_group_pk PRIMARY KEY (Group_ID, User_ID)
);


CREATE TABLE public.Group_Moderator (
                User_ID BIGINT NOT NULL,
                Group_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                CONSTRAINT group_moderator_pk PRIMARY KEY (User_ID)
);


CREATE SEQUENCE route_id_seq
start with 6;

CREATE TABLE public.Route (
                Route_ID BIGINT NOT NULL default nextval('route_id_seq'),
                City_ID BIGINT NOT NULL,
                Route_Begin geography NOT NULL,
                Route_End geography NOT NULL,
                Price NUMERIC,
                Driver_ID BIGINT NOT NULL,
                CONSTRAINT route_pk PRIMARY KEY (Route_ID)
);

CREATE SEQUENCE schedule_id_seq;

CREATE TABLE public.Schedule (
                Schedule_ID BIGINT NOT NULL default nextval('schedule_id_seq'),
                Route_ID BIGINT NOT NULL,
                Time_Of_Journey TIME,
                CONSTRAINT schedule_pk PRIMARY KEY (Schedule_ID)
);

CREATE SEQUENCE chat_id_seq;

CREATE TABLE public.Chat (
                Chat_ID BIGINT NOT NULL default nextval('chat_id_seq'),
                Route_ID BIGINT,
                Group_ID BIGINT,
                CONSTRAINT chat_pk PRIMARY KEY (Chat_ID)
);

CREATE SEQUENCE message_id_seq
start with 15;

CREATE TABLE public.Message (
                Message_ID BIGINT NOT NULL default nextval('message_id_seq'),
                Text VARCHAR,
                Date_Of_Sending TIMESTAMP NOT NULL,
                Chat_ID BIGINT NOT NULL,
                User_Message_ID BIGINT NOT NULL,
                CONSTRAINT message_pk PRIMARY KEY (Message_ID)
);

CREATE SEQUENCE journey_id_seq;

CREATE TABLE public.Journey (
                Journey_ID BIGINT NOT NULL default nextval('journey_id_seq'),
                Route_ID BIGINT NOT NULL,
                Date_Of_Journey DATE NOT NULL,
                Driver_ID BIGINT NOT NULL,
                CONSTRAINT journey_pk PRIMARY KEY (Journey_ID)
);


CREATE TABLE public.Passenger_in_Journey (
                Journey_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT passenger_in_journey_pk PRIMARY KEY (Journey_ID, User_ID)
);


CREATE TABLE public.Passenger_In_Route (
                Route_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT passenger_in_route_pk PRIMARY KEY (Route_ID, User_ID)
);


ALTER TABLE public.Report ADD CONSTRAINT moderator_report_fk
FOREIGN KEY (Moderator_ID)
REFERENCES public.Moderator (Moderator_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Group_Moderator ADD CONSTRAINT group_group_moderator_fk
FOREIGN KEY (Group_ID)
REFERENCES public.Group_1 (Group_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Chat ADD CONSTRAINT group_chat_fk
FOREIGN KEY (Group_ID)
REFERENCES public.Group_1 (Group_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.User_In_Group ADD CONSTRAINT group_user_in_group_fk
FOREIGN KEY (Group_ID)
REFERENCES public.Group_1 (Group_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Route ADD CONSTRAINT ______________fk
FOREIGN KEY (City_ID)
REFERENCES public.City (City_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.User_1 ADD CONSTRAINT city_user_fk
FOREIGN KEY (City_ID)
REFERENCES public.City (City_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Group_Moderator ADD CONSTRAINT user_group_moderator_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.User_In_Group ADD CONSTRAINT user_user_in_group_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Report ADD CONSTRAINT user_report_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Notification ADD CONSTRAINT user_notification_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.Passenger_in_Journey ADD CONSTRAINT user_passenger_in_journey_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Journey ADD CONSTRAINT user_journey_fk
FOREIGN KEY (Driver_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Route ADD CONSTRAINT user_route_fk
FOREIGN KEY (Driver_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_In_Route ADD CONSTRAINT user_passenger_in_route_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.Passenger_In_Route ADD CONSTRAINT ____________________________________fk
FOREIGN KEY (Route_ID)
REFERENCES public.Route (Route_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Journey ADD CONSTRAINT route_journey_fk
FOREIGN KEY (Route_ID)
REFERENCES public.Route (Route_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Chat ADD CONSTRAINT route_chat_fk
FOREIGN KEY (Route_ID)
REFERENCES public.Route (Route_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Schedule ADD CONSTRAINT route_schedule_fk
FOREIGN KEY (Route_ID)
REFERENCES public.Route (Route_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_in_Journey ADD CONSTRAINT journey_passenger_in_journey_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.Journey (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Message ADD CONSTRAINT user_message_fk
FOREIGN KEY (User_Message_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


