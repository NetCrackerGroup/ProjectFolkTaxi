INSERT INTO City(City_ID, City_Name, City_Map) VALUES (100, 'Moscow', '777000');
INSERT INTO City(City_ID, City_Name, City_Map) VALUES (47, 'Lipetsk', '777000');

INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (2, 1, 'Alex Shevtsov', 'alex@alex.com', '896345342');
INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (3, 47, 'Jimmy Hendrix', 'jimmy@jimmy.com', '865745350');
INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (4, 47, 'Bob Dylan', 'bob@bob.com', '865723250');
INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (5, 100, 'Artemiy Artemiev', 'artemiy@artemiy.com', '840923452');
INSERT INTO User_1(User_ID, City_ID, FIO, Email, Phone_Number) VALUES (6, 100, 'Artemiy Artemov', 'artemov@artemov.com', '840923452');



INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (1, 0);
INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (2, 0);
INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (3, 0);
INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (4, 0);
INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (5, 0);
INSERT INTO Driver_Rating(User_ID, Average_Mark) VALUES (6, 0);

INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (1, 0);
INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (2, 0);
INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (3, 0);
INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (4, 0);
INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (5, 0);
INSERT INTO Passenger_Rating(User_ID, Average_Mark) VALUES (6, 0);


INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (1, true, 1, 'well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (2, true, 5, 'not well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (3, true, 2, ' not well done', 2);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (4, false, 3, 'not well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (5, false, 3, 'not well done', 4);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (6, false, 4, 'well done', 5);