ALTER TABLE public.Passenger_Not_In_Route ADD CONSTRAINT user_passenger_not_in_route_fk
FOREIGN KEY (User_ID)
REFERENCES public.App_Users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Passenger_Not_In_Route ADD CONSTRAINT route_passenger_not_in_route_fk
FOREIGN KEY (Route_ID)
REFERENCES public.Routes (Route_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;