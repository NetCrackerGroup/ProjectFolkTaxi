CREATE TABLE public.User_Not_In_Group (
                Group_ID BIGINT NOT NULL,
                User_ID BIGINT NOT NULL,
                CONSTRAINT user_not_in_group_pk PRIMARY KEY (Group_ID, User_ID)
);