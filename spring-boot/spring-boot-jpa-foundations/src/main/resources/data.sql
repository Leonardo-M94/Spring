INSERT INTO GENRE(ID, NAME) VALUES
(1, 'fantasy'), (2, 'fantastic');

INSERT INTO films(ID, TITLE, GENRE_ID) VALUES
(1, 'Harry Potter', 1),
(2, 'Back to the future', 2);

INSERT INTO PLACE(ID, NAME, FILM_ID) VALUES
(1, 'London square', 1),
(2, 'Train', 1),
(3, 'USA', 2);