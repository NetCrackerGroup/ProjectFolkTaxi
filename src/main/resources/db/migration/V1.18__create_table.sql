CREATE SEQUENCE group_notification_id;

CREATE TABLE public.Group_Notification (
    Group_Notification_ID BIGINT NOT NULL default nextval('group_notification_id'),
    Group_ID BIGINT NOT NULL,
    Content BIGINT NOT NULL,
    CONSTRAINT group_notification_pk PRIMARY KEY (Group_Notification_ID)
);

ALTER TABLE public.Group_Notification ADD CONSTRAINT Group_fk
FOREIGN KEY (Group_ID)
REFERENCES public.user_groups (Group_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;