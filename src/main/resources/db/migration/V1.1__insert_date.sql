INSERT INTO Moderator(Moderator_ID, FIO) VALUES (1 , 'Quentin Tarantino');
INSERT INTO Moderator(Moderator_ID, FIO) VALUES (2 , 'Quentin Tarantino');
INSERT INTO City(City_ID, City_Name, City_Map) VALUES (1, 'Voronezh' , 'gm.voronezh');
INSERT INTO User_1(User_ID, City_ID, Role_name, FIO, Email, Phone_Number,Password ) VALUES (1, 1, 'ROLE_USER','Leonardo DiCaprio' , 'oskar2016@gmail.com' , '11111', '$2a$04$rkLAV8Sd7XzYiWBkUl0L6uJQND2HFox86BjcztV5FKAzyc7/8UA/i');

INSERT INTO Report(Report_ID , Report_Text, Report_Reason, Was_Considered, Moderator_ID, User_ID) VALUES(1 , 'hsf' , 'saf' , true, 1, 1);

INSERT INTO Report(Report_ID , Report_Text, Report_Reason, Was_Considered, Moderator_ID, User_ID)  VALUES (2 , 'aaaaa', 'ooooo', false, 1, 1);

INSERT INTO Notification(Notification_ID, Text, Delivery_Channel, Was_Watched, User_ID) VALUES (1 , 'Завтра едешь?' , 'Голубиная почта' , false , 1);

INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:1337/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');