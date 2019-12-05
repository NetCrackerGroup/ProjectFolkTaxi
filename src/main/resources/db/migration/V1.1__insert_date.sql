INSERT INTO Moderator(Moderator_ID, FIO) VALUES (1 , 'Quentin Tarantino');
INSERT INTO City(City_ID, City_Name, City_Map) VALUES (1, 'Voronezh' , 'gm.voronezh');
INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (1 , 1, 'Leonardo DiCaprio' , 'oskar2016@gmail.com' , '11111');

INSERT INTO Report(Report_ID , Report_Text, Report_Reason, Was_Considered, Moderator_ID, User_ID) VALUES(1 , 'hsf' , 'saf' , true, 1, 1);