CREATE TABLE public.Passenger_Not_In_Route (
                Route_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT passenger_not_in_route_pk PRIMARY KEY (Route_ID, User_ID)
);