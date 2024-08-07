-- database: ../database/data.sqlite

DROP TABLE IF EXISTS HighScores;
CREATE TABLE HighScores (
    idHighScores                          INTEGER PRIMARY KEY AUTOINCREMENT,
    player_name                 TEXT NOT NULL,
    score                       INTEGER NOT NULL,
    survived_time               varchar(5) NOT NULL ,
    FechaCrea                   DATETIME DEFAULT(datetime('now', 'localtime'))
);

