-- database: ../database/data.sqlite

DROP TABLE IF EXISTS high_scores;
CREATE TABLE high_scores (
    id                          INTEGER PRIMARY KEY AUTOINCREMENT,
    player_name                 TEXT NOT NULL,
    score                       INTEGER NOT NULL,
    survived_time               varchar(5) NOT NULL ,
    FechaCrea                   DATETIME DEFAULT(datetime('now', 'localtime'))
);

