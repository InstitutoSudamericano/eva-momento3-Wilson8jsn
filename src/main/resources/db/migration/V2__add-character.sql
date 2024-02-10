CREATE TABLE IF NOT EXISTS character (
    id SERIAL,
    description VARCHAR(100) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    age INT NOT NULL,
    scene_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );