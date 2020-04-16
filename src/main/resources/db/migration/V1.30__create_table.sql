CREATE SEQUENCE complain_id;

CREATE TABLE public.Complains (
    Complain_ID BIGINT NOT NULL default nextval('complain_id'),
    User_ID BIGINT NOT NULL,
    Adresat_ID BIGINT NOT NULL,
    Complain_Text VARCHAR ,
    CONSTRAINT complain_pk PRIMARY KEY (Complain_ID)
);

ALTER TABLE public.Complains ADD CONSTRAINT Complain_user_fk
FOREIGN KEY (User_ID)
REFERENCES public.App_Users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Complains ADD CONSTRAINT Complain_adresat_fk
FOREIGN KEY (Adresat_ID)
REFERENCES public.App_Users (User_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;