INSERT INTO pet(id, breed) VALUES
(1, 'Cat'),
(2, 'Dog'),
(3, 'Rat'),
(4, 'Parrot'),
(5, 'Rabbit');

INSERT INTO emails(id, email, password) VALUES
(1, 'user1-Ivanov@mail.ru', 'r46RbUIN09'),
(2, 'Ant.Petrov-95@yandex.ru', 'dki23ERS8323'),
(3, 'Kovaleva.Anna.Vas@gmail.com', 'fsds78REW78J'),
(4, 'Nik-Nik72@mail.ru', 'qwerty123Jpk'),
(5, 'Test-qwerty123@mail.ru', 'jGhF409BLki');

INSERT INTO users(id, fio, birthday, male, email_id, pet_id) VALUES
(1, 'Ivanov Ivan Ivanovich', '1990-11-10', TRUE, 1, 3),
(2, 'Petrov Anton Mihaylovich', '1995-07-19', TRUE, 2, 2),
(3, 'Kovaleva Anna Vasilyevna', '1989-01-23', FALSE, 3, 1),
(4, 'Nikonenko Nikolay Petrovich', '1972-10-15', TRUE, 4, 2);