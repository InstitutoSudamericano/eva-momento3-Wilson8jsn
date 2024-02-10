CREATE TABLE IF NOT EXISTS film (
    id SERIAL,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    duration INT NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS scene (
    id SERIAL,
    description VARCHAR(100) NOT NULL,
    budget VARCHAR(100),
    hours INT NOT NULL,
    film_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (film_id) REFERENCES film(id)
    );
