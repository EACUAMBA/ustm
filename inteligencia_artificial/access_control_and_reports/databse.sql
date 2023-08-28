CREATE TABLE user (
    id       INTEGER       PRIMARY KEY AUTOINCREMENT,
    username VARCHAR (255)
);

CREATE TABLE access_reports (
    id      INTEGER  PRIMARY KEY AUTOINCREMENT,
    user_id INT,
    date    DATETIME NOT NULL,
    CONSTRAINT FK_access_report_user FOREIGN KEY (
        user_id
    )
    REFERENCES id (user)
);