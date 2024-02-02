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
    seconds INT NOT NULL,
    movie_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (movie_id) REFERENCES film(id)

    );

CREATE TABLE IF NOT EXISTS character (
    id SERIAL,
    description VARCHAR(100) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    scene_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );