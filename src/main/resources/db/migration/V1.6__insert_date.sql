CREATE TABLE OAUTH_CLIENT_DETAILS (
                                      client_id  VARCHAR(255) PRIMARY KEY,
                                      resource_ids  VARCHAR(255),
                                      client_secret  VARCHAR(255),
                                      scope  VARCHAR(255),
                                      authorized_grant_types  VARCHAR(255),
                                      web_server_redirect_uri  VARCHAR(255),
                                      authorities  VARCHAR(255),
                                      access_token_validity  BIGINT,
                                      refresh_token_validity  BIGINT,
                                      additional_information  VARCHAR(4096),
                                      autoapprove VARCHAR(255)
);

CREATE TABLE OAUTH_CLIENT_TOKEN (
                                    TOKEN_ID VARCHAR(255),
                                    TOKEN bytea,
                                    AUTHENTICATION_ID VARCHAR(255) PRIMARY KEY,
                                    USER_NAME VARCHAR(255),
                                    CLIENT_ID VARCHAR(255)
);

CREATE TABLE OAUTH_ACCESS_TOKEN (
                                    TOKEN_ID VARCHAR(255),
                                    TOKEN bytea,
                                    AUTHENTICATION_ID VARCHAR(255) PRIMARY KEY,
                                    USER_NAME VARCHAR(255),
                                    CLIENT_ID VARCHAR(255),
                                    AUTHENTICATION bytea,
                                    REFRESH_TOKEN VARCHAR(255)
);

CREATE TABLE OAUTH_REFRESH_TOKEN (
                                     TOKEN_ID VARCHAR(255),
                                     TOKEN bytea,
                                     AUTHENTICATION bytea
);

CREATE TABLE OAUTH_CODE (
                            CODE VARCHAR(255),
                            AUTHENTICATION bytea
);

CREATE TABLE OAUTH_APPROVALS (
                                 USERID VARCHAR(255),
                                 CLIENTID VARCHAR(255),
                                 SCOPE VARCHAR(255),
                                 STATUS VARCHAR(10),
                                 EXPIRESAT TIMESTAMP,
                                 LASTMODIFIEDAT TIMESTAMP
);
