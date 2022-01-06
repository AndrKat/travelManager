DROP TABLE IF EXISTS customers cascade;
DROP TABLE IF EXISTS trips cascade;
DROP TABLE IF EXISTS knots cascade;
DROP TABLE IF EXISTS points_of_interest cascade;
DROP TABLE IF EXISTS connections cascade;

CREATE TABLE IF NOT EXISTS customers(
    id bigserial NOT NULL,
    name_of_customer varchar(255),
    email varchar(255),
    password varchar(255),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS trips(
    id bigserial NOT NULL,
    name_of_trip varchar(100),
    start_of_trip varchar(100),
    finish_of_trip varchar(100),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS knots(
    id bigserial NOT NULL,
    place_of_transfer varchar(100),
    date_of_transfer date,
    numbers_of_days integer,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS points_of_interest(
    id bigserial NOT NULL,
    name_of_place varchar(100),
    coordinates varchar(100),
    description text,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS connections (
    id bigserial NOT NULL,
    type_of_movement varchar(255),
    availability_of_tickets boolean,
    price_of_ticket decimal,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS customer_trip(
  customer_id bigserial NOT NULL,
  trip_id bigserial NOT NULL,
  PRIMARY KEY (customer_id, trip_id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (trip_id) REFERENCES trips(id)
);

INSERT INTO customers (name_of_customer, email, password)
VALUES ('Aleksandr Ivanov', 'Ivanov@gmail.com', '12Hgh8G09'),
       ('Petr Sokolov', 'Sokolov@gmail.com', 'S2kh31fs'),
       ('Elena Nikolaeva', 'ElenaNikolaeva@gmail.com', 'S5753197lhs');

INSERT INTO trips (name_of_trip, start_of_trip, finish_of_trip)
VALUES ('Italy', 'Brest', 'Rome'),
       ('Spain', 'Brest', 'Madrid'),
       ('Japan', 'Minsk', 'Tokio');

INSERT INTO knots (place_of_transfer,date_of_transfer, numbers_of_days)
VALUES ('Minsk', '2021-12-22', 0),
       ('Warsaw', '2021-12-22', 1),
       ('Frankfurt-na-Mayne', '2021-12-22', 1);

INSERT INTO points_of_interest (name_of_place, coordinates, description)
VALUES ('Национальная библиотека Беларуси', 'Минск, Проспект Независимости, 116', 'Национальная библиотека' ||
       ' Беларуси является объектом культурного наследия белорусов, собирает, хранит и предоставляет в' ||
       ' пользование универсальный фонд документов, книг, печатных и электронных изданий, отражающих ' ||
       'накопленные знания человечества и имеющих, в первую очередь, отношение к Беларуси.'),
       ('Белорусский государственный музей истории Великой Отечественной войны', 'Минск, Проспект Победителей, 8',
       'Деятельность Белорусского государственного музея истории Великой Отечественной войны направлена на' ||
       ' сохранение, изучение и пропаганду документальных и материальных свидетельств о подвиге и жертвах' ||
       ' белорусского народа в годы войны во имя сохранения мира на земле.'),
       ('Старый город', 'Варшава, Рыночная площадь', 'Варшавский Старый город  — старейший исторический район' ||
       ' Варшавы.'),
       ('Королевский замок', 'Варшава,Замковая площадь, 4', 'Замок является памятником национальной ' ||
       'культуры, кроме того, что существует постоянная экспозиция, постоянно проходят выставки, концерты,' ||
       ' симпозиумы.'),
       ('Площадь Ремерберг', 'Франкфурт-на-Майне, Площадь Ремерберг', 'Одним из самых красивейших мест' ||
       ' Франкфурта считается площадь Ремерберг, на которой в свое время проводились ярмарки и коронации. ' ||
       'Площадь Ремер, как ее еще называют, можно увидеть на многих открытках и сувенирах. Ее потрясающая ' ||
       'архитектура дает возможность окунуться на несколько столетий назад.'),
       ('Старая опера', 'Франкфурт-на-Майне, Площадь Опера, 1', 'Старая Опера - одно из самых известных зданий' ||
       ' Франкфурта. Она была открыта в 1880 году. На здании можно прочесть девиз "Истинному, прекрасному,' ||
       ' доброму".');

INSERT INTO connections (type_of_movement, availability_of_tickets, price_of_ticket)
VALUES ('bus', true, 20.99),
       ('train', true, 35.99),
       ('plane', true, 58.99);

