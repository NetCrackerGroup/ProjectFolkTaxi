<<<<<<< HEAD

CREATE TABLE public.Moderator (
                Moderator_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                CONSTRAINT moderator_pk PRIMARY KEY (Moderator_ID)
);


CREATE TABLE public.Group_1 (
                Group_ID BIGINT NOT NULL,
                Group_Name VARCHAR NOT NULL,
                Link VARCHAR NOT NULL,
                CONSTRAINT group_1_pk PRIMARY KEY (Group_ID)
);


CREATE TABLE public.City (
                City_ID BIGINT NOT NULL,
                City_Name VARCHAR NOT NULL,
                City_Map VARCHAR NOT NULL,
                CONSTRAINT city_pk PRIMARY KEY (City_ID)
);


CREATE TABLE public.User_1 (
                User_ID BIGINT NOT NULL,
                City_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                Email VARCHAR,
                Phone_Number VARCHAR,
                CONSTRAINT user_id PRIMARY KEY (User_ID)
);


CREATE TABLE public.Driver_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT driver_rating_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Passenger_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT passenger_rating_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Review (
                Review_ID BIGINT NOT NULL,
                Mark NUMERIC NOT NULL,
                Additional_Text VARCHAR,
                User_ID BIGINT NOT NULL,
                CONSTRAINT review_pk PRIMARY KEY (Review_ID)
);


CREATE TABLE public.Notification (
                Notification_ID BIGINT NOT NULL,
                Text VARCHAR NOT NULL,
                Delivery_Channel VARCHAR NOT NULL,
                Was_Watched BOOLEAN NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT notification_pk PRIMARY KEY (Notification_ID)
);


CREATE TABLE public.Report (
                Report_ID BIGINT NOT NULL,
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
                CONSTRAINT moderator_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Route (
                Route_ID BIGINT NOT NULL,
                City_ID BIGINT NOT NULL,
                Route_Begin VARCHAR NOT NULL,
                Route_End VARCHAR NOT NULL,
                Price NUMERIC,
                Driver_ID BIGINT NOT NULL,
                CONSTRAINT route_pk PRIMARY KEY (Route_ID)
);


CREATE TABLE public.Schedule (
                Schedule_ID BIGINT NOT NULL,
                Schedule_Day VARCHAR NOT NULL,
                Route_ID BIGINT NOT NULL,
                Time_Of_Journey TIME,
                CONSTRAINT schedule_pk PRIMARY KEY (Schedule_ID)
);


CREATE TABLE public.Chat (
                Chat_ID BIGINT NOT NULL,
                Route_ID BIGINT,
                Group_ID BIGINT,
                CONSTRAINT chat_pk PRIMARY KEY (Chat_ID)
);


CREATE TABLE public.Message (
                Message_ID BIGINT NOT NULL,
                Text VARCHAR,
                Date_Of_Sending TIMESTAMP NOT NULL,
                Chat_ID BIGINT NOT NULL,
                CONSTRAINT message_pk PRIMARY KEY (Message_ID)
);


CREATE TABLE public.Journey (
                Journey_ID BIGINT NOT NULL,
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

ALTER TABLE public.Passenger_Rating ADD CONSTRAINT user_passenger_rating_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Driver_Rating ADD CONSTRAINT user_driver_rating_fk
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

ALTER TABLE public.Review ADD CONSTRAINT driver_rating_review_fk
FOREIGN KEY (User_ID)
REFERENCES public.Driver_Rating (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Review ADD CONSTRAINT passenger_rating_review_fk
FOREIGN KEY (User_ID)
REFERENCES public.Passenger_Rating (User_ID)
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

ALTER TABLE public.Message ADD CONSTRAINT chat_message_fk
FOREIGN KEY (Chat_ID)
REFERENCES public.Chat (Chat_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_in_Journey ADD CONSTRAINT journey_passenger_in_journey_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.Journey (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
=======
CREATE TABLE public.Moderator (
                Moderator_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                CONSTRAINT moderator_pk PRIMARY KEY (Moderator_ID)
);


CREATE TABLE public.Group_1 (
                Group_ID BIGINT NOT NULL,
                Group_Name VARCHAR NOT NULL,
                Link VARCHAR NOT NULL,
                CONSTRAINT group_1_pk PRIMARY KEY (Group_ID)
);


CREATE TABLE public.City (
                City_ID BIGINT NOT NULL,
                City_Name VARCHAR NOT NULL,
                City_Map VARCHAR NOT NULL,
                CONSTRAINT city_pk PRIMARY KEY (City_ID)
);


CREATE TABLE public.User_1 (
                User_ID BIGINT NOT NULL,
                City_ID BIGINT NOT NULL,
                FIO VARCHAR NOT NULL,
                Email VARCHAR,
                Phone_Number VARCHAR,
                CONSTRAINT user_id PRIMARY KEY (User_ID)
);


CREATE TABLE public.Driver_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT driver_rating_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Passenger_Rating (
                User_ID BIGINT NOT NULL,
                Average_Mark NUMERIC,
                CONSTRAINT passenger_rating_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Review (
                Review_ID BIGINT NOT NULL,
                Mark NUMERIC NOT NULL,
                Additional_Text VARCHAR,
                User_ID BIGINT NOT NULL,
                CONSTRAINT review_pk PRIMARY KEY (Review_ID)
);


CREATE TABLE public.Notification (
                Notification_ID BIGINT NOT NULL,
                Text VARCHAR NOT NULL,
                Delivery_Channel VARCHAR NOT NULL,
                Was_Watched BOOLEAN NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT notification_pk PRIMARY KEY (Notification_ID)
);


CREATE TABLE public.Report (
                Report_ID BIGINT NOT NULL,
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
                CONSTRAINT moderator_pk PRIMARY KEY (User_ID)
);


CREATE TABLE public.Route (
                Route_ID BIGINT NOT NULL,
                City_ID BIGINT NOT NULL,
                Route_Begin VARCHAR NOT NULL,
                Route_End VARCHAR NOT NULL,
                Price NUMERIC,
                Driver_ID BIGINT NOT NULL,
                CONSTRAINT route_pk PRIMARY KEY (Route_ID)
);


CREATE TABLE public.Schedule (
                Schedule_ID BIGINT NOT NULL,
                Schedule_Day VARCHAR NOT NULL,
                Route_ID BIGINT NOT NULL,
                Time_Of_Journey TIME,
                CONSTRAINT schedule_pk PRIMARY KEY (Schedule_ID)
);


CREATE TABLE public.Chat (
                Chat_ID BIGINT NOT NULL,
                Route_ID BIGINT,
                Group_ID BIGINT,
                CONSTRAINT chat_pk PRIMARY KEY (Chat_ID)
);


CREATE TABLE public.Message (
                Message_ID BIGINT NOT NULL,
                Text VARCHAR,
                Date_Of_Sending TIMESTAMP NOT NULL,
                Chat_ID BIGINT NOT NULL,
                CONSTRAINT message_pk PRIMARY KEY (Message_ID)
);


CREATE TABLE public.Journey (
                Journey_ID BIGINT NOT NULL,
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

ALTER TABLE public.Passenger_Rating ADD CONSTRAINT user_passenger_rating_fk
FOREIGN KEY (User_ID)
REFERENCES public.User_1 (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Driver_Rating ADD CONSTRAINT user_driver_rating_fk
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

ALTER TABLE public.Review ADD CONSTRAINT driver_rating_review_fk
FOREIGN KEY (User_ID)
REFERENCES public.Driver_Rating (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Review ADD CONSTRAINT passenger_rating_review_fk
FOREIGN KEY (User_ID)
REFERENCES public.Passenger_Rating (User_ID)
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

ALTER TABLE public.Message ADD CONSTRAINT chat_message_fk
FOREIGN KEY (Chat_ID)
REFERENCES public.Chat (Chat_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_in_Journey ADD CONSTRAINT journey_passenger_in_journey_fk
FOREIGN KEY (Journey_ID)
REFERENCES public.Journey (Journey_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
>>>>>>> master
