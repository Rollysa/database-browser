---
---Schema for water sport---
---
DROP SCHEMA IF EXISTS sport CASCADE;
CREATE SCHEMA IF NOT EXISTS sport;

---
---Water sport---
---
CREATE TABLE sport.water_sport
(
    id       INT8         NOT NULL,
    title    VARCHAR(255) NOT NULL,
    distance INT8         NOT NULL
);

ALTER TABLE sport.water_sport
    ADD CONSTRAINT pk_water_sport PRIMARY KEY (id);

INSERT INTO sport.water_sport(id, title, distance)
VALUES (1, 'Canoeing', 5),
       (2, 'Rowing', 10),
       (3, 'Windsurfing', 15),
       (4, 'Yachting', 30);

---
---Swim---
---
CREATE TABLE sport.swim
(
    id       INT8        NOT NULL,
    title    VARCHAR(255) NOT NULL,
    style    VARCHAR(255) NOT NULL,
    distance FLOAT8       NOT NULL
);

ALTER TABLE sport.swim
    ADD CONSTRAINT pk_swim PRIMARY KEY (id);

INSERT INTO sport.swim(id, title, style, distance)
VALUES (1, 'Evening swim', 'Backstroke', 80.9),
       (2, 'Swim', 'Breaststroke', 50.4),
       (3, 'Running swim', 'Butterfly', 26.2),
       (4, 'Swim', 'Freestyle', 78.5);
