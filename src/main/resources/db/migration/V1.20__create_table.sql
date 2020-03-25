CREATE SEQUENCE info_id_seq;

CREATE TABLE public.Info (
    Info_ID BIGINT NOT NULL default nextval('info_id_seq'),
    Info_Key VARCHAR NOT NULL,
    Info_Subject VARCHAR  NOT NULL,
    Info_Text VARCHAR  NOT NULL,
    CONSTRAINT info_pk PRIMARY KEY (Info_ID) ,
    CONSTRAINT info_unique UNIQUE (Info_Key)
);