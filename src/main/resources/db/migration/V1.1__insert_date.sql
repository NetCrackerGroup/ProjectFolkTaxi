INSERT INTO Moderator(Moderator_ID, FIO) VALUES (1 , 'Quentin Tarantino');
INSERT INTO Moderator(Moderator_ID, FIO) VALUES (2 , 'Quentin Tarantino');
INSERT INTO City(City_ID, City_Name, City_Map) VALUES (1, 'Voronezh' , 'gm.voronezh');
INSERT INTO User_1(User_ID, City_ID, Role_name, FIO, Email, Phone_Number,Password ) VALUES (1, 1, 'ROLE_USER','Leonardo DiCaprio' , 'oskar2016@gmail.com' , '11111', '$2a$04$rkLAV8Sd7XzYiWBkUl0L6uJQND2HFox86BjcztV5FKAzyc7/8UA/i');

INSERT INTO Report(Report_ID , Report_Text, Report_Reason, Was_Considered, Moderator_ID, User_ID) VALUES(1 , 'hsf' , 'saf' , true, 1, 1);

INSERT INTO Report(Report_ID , Report_Text, Report_Reason, Was_Considered, Moderator_ID, User_ID)  VALUES (2 , 'aaaaa', 'ooooo', false, 1, 1);

INSERT INTO Notification(Notification_ID, Text, Delivery_Channel, Was_Watched, User_ID) VALUES (1 , 'Завтра едешь?' , 'Голубиная почта' , false , 1);