
ALTER TABLE Notifications DROP COLUMN was_watched;
ALTER TABLE Notifications DROP COLUMN text;
ALTER TABLE Notifications DROP COLUMN delivery_channel;
ALTER TABLE Notifications ADD Info_ID BIGINT ;
ALTER TABLE Notifications ADD time TIMESTAMP ;


create table public.InfoToNotification (
    Notification_ID BIGINT NOT NULL,
    InfoMap_ID  BIGINT
);

CREATE SEQUENCE infoMap_id_seq;

create table public.InfoMap (
    InfoMap_ID BIGINT NOT NULL default nextval('infoMap_id_seq'),
    InfoKey VARCHAR(255) ,
    InfoValue VARCHAR (255),
    CONSTRAINT infoMap_pk PRIMARY KEY (InfoMap_ID)
);



>>>>>>> origin/master
