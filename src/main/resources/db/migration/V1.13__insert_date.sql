
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('spring-security-oauth2-read-client', '',
           '$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
        'read', 'password,authorization_code,refresh_token,implicit', 'USER, ADMIN', 9, 2592000);
/*spring-security-oauth2-read-client-password1234*/
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('spring-security-oauth2-read-write-client', '',
           '$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
        'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 9, 2592000);
/*spring-security-oauth2-read-write-client-password1234*/