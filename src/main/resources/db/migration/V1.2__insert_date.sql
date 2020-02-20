INSERT INTO City(City_ID, City_Name, City_Map) VALUES (100, 'Moscow', '777000');
INSERT INTO City(City_ID, City_Name, City_Map) VALUES (47, 'Lipetsk', '777000');

INSERT INTO User_1(User_ID, City_ID,Role_name, FIO, Email, Phone_Number, Password) VALUES (2, 1,'ROLE_USER', 'Alex Shevtsov', 'alex@alex.com', '896345342', '$2a$04$xgnNAO4k8F3nV6x8.Z2cy.INZXhGRt4wqj9F3HLO8Cv2bQjK2XTwa');
INSERT INTO User_1(User_ID, City_ID,Role_name, FIO, Email, Phone_Number, Password) VALUES (3, 47,'ROLE_USER', 'Jimmy Hendrix', 'jimmy@jimmy.com', '865745350', '$2a$04$7NuaVD3eF5gFmKJX0Pfpt.WEgFdpSXDNWXLRAAloHrUnK5KpH6Bnu');
INSERT INTO User_1(User_ID, City_ID,Role_name, FIO, Email, Phone_Number, Password) VALUES (4, 47,'ROLE_USER', 'Bob Dylan', 'bob@bob.com', '865723250', '$2a$04$V3E6ghq86cpHmWIs7I41mucXkqG2/lWFGBSdiwuySGpfsSfmpUA96');
INSERT INTO User_1(User_ID, City_ID,Role_name, FIO, Email, Phone_Number, Password) VALUES (5, 100,'ROLE_USER', 'Artemiy Artemiev', 'artemiy@artemiy.com', '840923452', '$2a$04$KIDfVNIrB3h/AQZLkHf8Ru4MqkXxKqK4EsPkWYGiEeInTyvjyQg5G');
INSERT INTO User_1(User_ID, City_ID,Role_name, FIO, Email, Phone_Number, Password) VALUES (6, 100,'ROLE_ADMIN', 'Artemiy Artemov', 'artemov@artemov.com', '840923452', '$2a$04$lexjoWkJLmk4pttvByM09Ofe/J2gxlRYsJhFpRFVNurcxMTlieg3a');

INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (1, true, 1, 'well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (2, true, 5, 'not well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (3, true, 2, ' not well done', 2);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (4, false, 3, 'not well done', 1);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (5, false, 3, 'not well done', 4);
INSERT INTO Review(Review_ID, Is_passenger, Mark, Additional_Text, User_ID) VALUES (6, false, 4, 'well done', 5);