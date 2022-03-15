INSERT INTO GENRE(ID, NAME) VALUES
(1, 'fantasy'), (2, 'horror'), (3, 'drama');

INSERT INTO FILMS(ID, TITLE, GENRE_ID) VALUES
(1, 'Lord of the Rings', 1),
(2, 'Scream', 2);

INSERT INTO PLACE(ID, NAME, FILM_ID) VALUES
(1, 'New Zealand', 1),
(2, 'USA', 1),
(3, 'Russia', 2);