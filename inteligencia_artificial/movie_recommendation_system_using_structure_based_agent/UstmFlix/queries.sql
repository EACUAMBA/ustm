create table movie( 
`index` INTEGER, 
 director_name TEXT, 
 duration REAL, 
 actor_1_name TEXT, 
 actor_2_name TEXT, 
 actor_3_name TEXT, 
 genres TEXT, 
 title TEXT, 
 `language` TEXT, 
 country TEXT, 
 title_year INTEGER, 
 imdb_score REAL 
 );

 INSERT INTO (`index`,  director_name, duration, actor_1_name, actor_2_name, actor_3_name, genres, title, `language`, country, title_year, imdb_score) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

 create table user (
    username TEXT,
    `name` TEXT,
    `password` TEXT
 )

 create table movie_user_rating(
    username TEXT,
    movie_index INTEGER,
    `like` INTEGER,
    hate INTEGER,
    ignored INTEGER,
    already_see_it INTEGER
 )