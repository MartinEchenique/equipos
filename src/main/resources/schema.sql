CREATE TABLE TEAM(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR NOT NULL,
    LEAGUE VARCHAR NOT NULL,
    COUNTRY VARCHAR NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE APPUSER(
    USERNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL,
    PRIMARY KEY (USERNAME)
);