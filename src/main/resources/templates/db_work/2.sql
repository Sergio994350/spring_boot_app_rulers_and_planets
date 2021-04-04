-- заполняем таблицы в бд тестовыми данными

insert into planet (id, name, ruler_id) values
(1, 'Athypso', 1),
(2, 'Baotania', 1),
(3, 'Esania', 2),
(4, 'Dyke 2AC', 2),
(5, 'Iubos', 3),
(6, 'Ethenope', 4),
(7, 'Briraria', 5),
(8, 'Gruna 2V1', 5),
(9, 'Lunzualea', 6),
(10, 'Menope', 10),
(11, 'Dreeclite', 11),
(12, 'Kucorth', 11),
(13, 'Troyehines', 12),
(14, 'Ocriocarro', 12),
(15, 'Ugryke', 12),
(16, 'Vibrypso', 14),
(17, 'Eophus', 14),
(18, 'Mezuno', 14),
(19, 'Chepatera', 14),
(20, 'Pomia', 14);

insert into ruler (id, name, age) values
(1, 'Oemis', 30),
(2, 'Hoenar', 40),
(3, 'Detnos', 50),
(4, 'Xymos', 35),
(5, 'Hexdarr', 45),
(6, 'Liddum', 55),
(7, 'Fitia', 36),
(8, 'Novais', 46),
(9, 'Rolias', 56),
(10, 'Eharin', 66),
(11, 'Gemos', 37),
(12, 'Sytteus', 47),
(13, 'Loren', 57),
(14, 'Nilir', 67),
(15, 'Senir', 48);

insert into ruler_of_planet (id, ruler_id, planet_id) values
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 2, 4),
(5, 3, 5),
(6, 4, 6),
(7, 5, 7),
(8, 5, 8),
(9, 6, 9),
(10, 10, 10),
(11, 11, 11),
(12, 11, 12),
(13, 12, 13),
(14, 12, 14),
(15, 12, 15),
(16, 14, 16),
(17, 14, 17),
(18, 14, 18),
(19, 14, 19),
(20, 14, 20),
(21, 7, 0),
(22, 8, 0),
(23, 9, 0),
(24, 13, 0),
(25, 15, 0);


insert into hibernate_sequence (next_val) values (26);