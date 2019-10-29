---
---Schema for delivery---
---
DROP SCHEMA IF EXISTS deliver CASCADE;
DROP SCHEMA IF EXISTS work CASCADE;

CREATE SCHEMA IF NOT EXISTS delivery;

---
---Person---
---
CREATE TABLE delivery.person
(
    id         INT8         NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);

ALTER TABLE delivery.person
    ADD CONSTRAINT pk_person PRIMARY KEY (id);

INSERT INTO delivery.person(id, first_name, last_name)
VALUES (1, 'Jan', 'Novotny'),
       (2, 'John', 'Newman'),
       (3, 'Anna', 'Cerna');

---
---Order---
---
CREATE TABLE delivery.order
(
    id           INT8 NOT NULL,
    order_number INT8 NOT NULL,
    person_id    INT8 NOT NULL
);

ALTER TABLE delivery.order
    ADD CONSTRAINT pk_order PRIMARY KEY (id);

ALTER TABLE delivery.order
    ADD CONSTRAINT fk_order_person_id
        FOREIGN KEY (person_id)
            REFERENCES delivery.person;

INSERT INTO delivery.order(id, order_number, person_id)
VALUES (1, 1234, 2),
       (2, 5678, 3),
       (3, 0112, 1),
       (4, 35813, 1);

---
---Schema for work---
---
CREATE SCHEMA IF NOT EXISTS work;

---
---Department---
---
CREATE TABLE work.department
(
    id   INT8         NOT NULL,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE work.department
    ADD CONSTRAINT pk_department PRIMARY KEY (id);

ALTER TABLE work.department
    ADD CONSTRAINT uk_department_name UNIQUE (name);

INSERT INTO work.department(id, name)
VALUES (1, 'IT crowd'),
       (2, 'DevOps'),
       (3, 'Dev'),
       (4, 'AI');

---
---Employee---
---
CREATE TABLE work.employee
(
    id            INT8         NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    department_id INT8         NOT NULL
);

ALTER TABLE work.employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id);

ALTER TABLE work.employee
    ADD CONSTRAINT fk_employee_department_id
        FOREIGN KEY (department_id)
            REFERENCES work.department;

INSERT INTO work.employee(id, first_name, last_name, department_id)
VALUES (1, 'Jan', 'Novotny', 2),
       (2, 'John', 'Newman', 1),
       (3, 'Anna', 'Cerna', 3),
       (4, 'Eva', 'Novotna', 4);
