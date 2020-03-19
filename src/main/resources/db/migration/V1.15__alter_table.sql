ALTER TABLE group_moderators DROP COLUMN fio;
ALTER TABLE group_moderators DROP CONSTRAINT group_moderator_pk;
ALTER TABLE group_moderators ADD CONSTRAINT group_moderator_pk PRIMARY KEY (User_Id, Group_ID);