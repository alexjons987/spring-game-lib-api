INSERT INTO publishers (publisher_id, name, founded)
    VALUES  (1, 'Valve', '1996-08-24'),
            (2, 'Nintendo', '1889-09-23');

INSERT INTO games (id, publisher_id, rating, release_date, genre, title)
    VALUES  (1, 1, 92.5, '2007-10-10', 'FPS', 'Team Fortress 2'),
            (2, 1, 96.0, '2004-11-16', 'FPS', 'Half-Life 2'),
            (3, 1, 89.0, '2011-02-15', 'Puzzle', 'Portal 2'),
            (4, 2, 97.5, '2017-03-03', 'Adventure', 'The Legend of Zelda: Breath of the Wild'),
            (5, 2, 92.3, '1996-06-23', 'Platformer', 'Super Mario 64'),
            (6, 2, 91.0, '2006-11-19', 'Action-Adventure', 'The Legend of Zelda: Twilight Princess'),
            (7, 1, 90.1, '2013-08-21', 'FPS', 'Dota 2'),
            (8, 2, 88.6, '2019-06-28', 'Platform Fighter', 'Super Mario Maker 2');

INSERT INTO roles (id, name)
    VALUES  (1, 'ROLE_ADMIN'),
            (2, 'ROLE_USER');

INSERT INTO users (id, username, password)
    VALUES  (1, 'admin', '$2a$12$Ps0AtdV6pO8LOXAFIUDXRO8lMxj6BFkBA/c2IsQdeph4tECInR0hW'),   -- PW: admin123
            (2, 'user',  '$2a$12$TFbtPIEU3PBK6isNQLdcMOFhbPFJwAR.NpQuP8Tf5qycR.iy4xmua');   -- PW: user123

-- assign roles
INSERT INTO user_roles (user_id, role_id)
    VALUES  (1, 1), -- admin -> admin
            (1, 2), -- admin -> user
            (2, 2); -- user -> user
